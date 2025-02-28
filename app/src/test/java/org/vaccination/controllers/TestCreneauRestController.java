package org.vaccination.controllers;

import static org.mockito.ArgumentMatchers.eq;

import java.time.LocalDate;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.vaccination.App;
import org.vaccination.entities.Centre;
import org.vaccination.entities.Creneau;
import org.vaccination.entities.Patient;
import org.vaccination.services.CentreService;
import org.vaccination.services.CreneauService;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestCreneauRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreneauService creneauService;

    @MockitoBean
    private CentreService centreService;

    @Captor
    private ArgumentCaptor<Creneau> creneauCaptor;

    @Captor
    private ArgumentCaptor<Patient> patientCaptor;

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetCreneau() throws Exception {
        Creneau creneauMock = new Creneau();
        creneauMock.setId(1);
        creneauMock.setHeure(12);
        creneauMock.setMinute(45);
        creneauMock.setDate(LocalDate.of(2024, 02, 28));
        
    
        Mockito.when(creneauService.findOneById(1)).thenReturn(creneauMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/creneau/1?validerVac=false").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.heure").value(12))
        .andExpect(MockMvcResultMatchers.jsonPath("$.minute").value(45))
        .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2024-02-28"));

    }

    @Test
    void itShouldNotGetCreneau() throws Exception {
        Creneau creneauMock = new Creneau();
        creneauMock.setId(1);
        creneauMock.setHeure(12);
        creneauMock.setMinute(45);
        creneauMock.setDate(LocalDate.of(2024, 02, 28));
        
    
        Mockito.when(creneauService.findOneById(1)).thenReturn(creneauMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/creneau/1?validerVac=false").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    void itShouldGetAllCreneaux() throws Exception {
        List<Creneau> creneauxMock = new ArrayList<Creneau>();

        Centre centre = new Centre();
        centre.setId(1);
        centre.setVille("Nancy");
        centre.setAdresse("2 rue Jean Lamour");

        Creneau creneau1 = new Creneau();
        creneau1.setId(1);
        creneau1.setHeure(13);
        creneau1.setMinute(00);
        creneau1.setEstReserve(false);
        creneau1.setCentre(centre);
        creneau1.setDate(LocalDate.of(2024, 02, 27));

        Creneau creneau2 = new Creneau();
        creneau2.setId(2);
        creneau2.setHeure(12);
        creneau2.setMinute(45);
        creneau1.setEstReserve(false);
        creneau2.setDate(LocalDate.of(2024, 02, 27));

        creneauxMock.add(creneau1);
        creneauxMock.add(creneau2);
        
        Mockito.when(centreService.findOneById(1)).thenReturn(centre);
        Mockito.when(creneauService.findAllByCentreAndDateAndEstReserve(centre, LocalDate.of(2024, 02, 27), false)).thenReturn(creneauxMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/creneaux").contentType(MediaType.APPLICATION_JSON)
        .param("centre", "1")
        .param("date", LocalDate.of(2024, 02, 27).toString())
        .param("estReserve", "false"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].date").value(LocalDate.of(2024, 02, 27).toString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].date").value(LocalDate.of(2024, 02, 27).toString()));

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldAddACreneau() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/creneau").content("""
                {
                   "date": "2024-02-28",
                   "heure": 12,
                   "minute": 45,
                     "centre": {
                          "id": 1
                     }
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(creneauService).create(creneauCaptor.capture());
        Assertions.assertThat(creneauCaptor.getValue().getDate()).isEqualTo(LocalDate.of(2024, 02, 28).toString());
        Assertions.assertThat(creneauCaptor.getValue().getHeure()).isEqualTo(12);
        Assertions.assertThat(creneauCaptor.getValue().getMinute()).isEqualTo(45);
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldUpdateCreneauWithPatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/creneau/1/patient").content("""
                {   
                    "id": 1,
                    "prenom": "Aurelien"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Patient patient = new Patient();
        patient.setId(1);
        patient.setPrenom("Aurelien");
        
        Mockito.verify(creneauService).updateCreneauPatient(eq(1), patientCaptor.capture());
        Assertions.assertThat(patientCaptor.getValue().getPrenom()).isEqualTo("Aurelien");
        Assertions.assertThat(patientCaptor.getValue().getId()).isEqualTo(1);
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldDeleteACreneau() throws Exception {
        Creneau creneauMock = new Creneau();
        creneauMock.setId(1);
        creneauMock.setDate(LocalDate.of(2024, 02, 28));
    
        Mockito.when(creneauService.findOneById(1)).thenReturn(creneauMock);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/creneau/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(creneauService).delete(eq(1));
    }

    
}
