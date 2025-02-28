package org.vaccination.controllers;

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
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
import org.vaccination.entities.Patient;
import org.vaccination.services.PatientService;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestPatientRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PatientService patientService;

    @Captor
    private ArgumentCaptor<Patient> patientCaptor;

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetPatient() throws Exception {
        Patient patientMock = new Patient();
        patientMock.setId(1);
        patientMock.setNom("Dupont");
        patientMock.setPrenom("Jean");
        patientMock.setEstVaccine(false);
    
        Mockito.when(patientService.findOneById(1)).thenReturn(patientMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/1").contentType(MediaType.APPLICATION_JSON).param("estVaccine", "false"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dupont"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("Jean"));

    }

    @Test
    void itShouldNotGetPatient() throws Exception {
        Patient patientMock = new Patient();
        patientMock.setId(1);
        patientMock.setNom("Dupont");
        patientMock.setPrenom("Jean");
        patientMock.setEstVaccine(false);
    
        Mockito.when(patientService.findOneById(1)).thenReturn(patientMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patient/1").contentType(MediaType.APPLICATION_JSON).param("estVaccine", "false"))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());
    
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetAllPatients() throws Exception {
        List<Patient> patientsMock = new ArrayList<Patient>();

        Patient patient1 = new Patient();
        patient1.setId(1);
        patient1.setNom("Dupont");
        patient1.setPrenom("Jean");
        patient1.setEstVaccine(false);

        Patient patient2 = new Patient();
        patient2.setId(2);
        patient2.setNom("Dupont");
        patient2.setPrenom("Jeanne");
        patient2.setEstVaccine(false);

        patientsMock.add(patient1);
        patientsMock.add(patient2);
    
        Mockito.when(patientService.findAllByNomAndPrenom(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(patientsMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients").contentType(MediaType.APPLICATION_JSON).param("nom", "null").param("prenom", "null"))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom").value("Jeanne"));
    }

    @Test
    void itShouldAddAPatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/patient").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "ville": "Vandoeuvre",
                   "estVaccine": false
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(patientService).create(patientCaptor.capture());
        Assertions.assertThat(patientCaptor.getValue().getVille()).isEqualTo("Vandoeuvre");
        Assertions.assertThat(patientCaptor.getValue().getPrenom()).isEqualTo("Aurelien");
        Assertions.assertThat(patientCaptor.getValue().getNom()).isEqualTo("Test");
        Assertions.assertThat(patientCaptor.getValue().getEstVaccine()).isEqualTo(false);
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldDeleteAPatient() throws Exception {
        Patient patientMock = new Patient();
        patientMock.setId(1);
        patientMock.setNom("Dupont");
        patientMock.setPrenom("Jean");
        patientMock.setEstVaccine(false);
    
        Mockito.when(patientService.findOneById(1)).thenReturn(patientMock);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patient/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(patientService).delete(eq(1));
    }

    @Test
    void itShouldNotDeleteAPatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/patient/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    
}
