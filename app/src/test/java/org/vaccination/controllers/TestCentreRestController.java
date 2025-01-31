package org.vaccination.controllers;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.vaccination.App;
import org.vaccination.entities.Centre;
import org.vaccination.services.CentreService;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCentreRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CentreService centreService;

    @Captor
    private ArgumentCaptor<Centre> centreCaptor;

    @Test
    void itShouldGetCentre() throws Exception {
        Centre centreMock = new Centre();
        centreMock.setId(1);
        centreMock.setVille("Nancy");
    
        Mockito.when(centreService.findOneById(1)).thenReturn(centreMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/centre/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.ville").value("Nancy"));

    }

    @Test
    void itShouldGetAllCentres() throws Exception {
        List<Centre> centresMock = new ArrayList<Centre>();

        Centre centre1 = new Centre();
        centre1.setId(1);
        centre1.setVille("Paris");

        Centre centre2 = new Centre(); 
        centre2.setId(2);
        centre2.setVille("Metz");

        centresMock.add(centre1);
        centresMock.add(centre2);
    
        Mockito.when(centreService.findAllByVille(null)).thenReturn(centresMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/centres").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].ville").value("Paris"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].ville").value("Metz"));
    }

    @Test
    void itShouldAddACentre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/super-admin/centre").content("""
                {
                   "ville": "Vandoeuvre",
                   "adresse": "2 rue Jean Lamour",
                   "nom": "Centre de Vandoeuvre" 
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(centreService).create(centreCaptor.capture());
        Assertions.assertThat(centreCaptor.getValue().getVille()).isEqualTo("Vandoeuvre");
        Assertions.assertThat(centreCaptor.getValue().getAdresse()).isEqualTo("2 rue Jean Lamour");
        Assertions.assertThat(centreCaptor.getValue().getNom()).isEqualTo("Centre de Vandoeuvre");
    }

    @Test
    void itShouldUpdateACentre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/super-admin/centre/1").content("""
                {   
                    "id": 1,
                    "ville": "Vandoeuvre"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(centreService).update(eq(1), centreCaptor.capture());
        Assertions.assertThat(centreCaptor.getValue().getVille()).isEqualTo("Vandoeuvre");
        Assertions.assertThat(centreCaptor.getValue().getId()).isEqualTo(1);
    }

    @Test
    void itShouldDeleteACentre() throws Exception {
        Centre centreMock = new Centre();
        centreMock.setId(1);
        centreMock.setVille("Nancy");
    
        Mockito.when(centreService.findOneById(1)).thenReturn(centreMock);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/super-admin/centre/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(centreService).delete(eq(1));
    }

    
}
