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
import org.vaccination.entities.Utilisateur;
import org.vaccination.services.UtilisateurService;

@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class TestUtilisateurRestController {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UtilisateurService utilisateurService;

    @Captor
    private ArgumentCaptor<Utilisateur> utilisateurCaptor;

    Utilisateur utilisateurMock(String role) throws Exception {
        Utilisateur utilisateurMock = new Utilisateur();
        utilisateurMock.setId(1);
        utilisateurMock.setNom("Dupont");
        utilisateurMock.setPrenom("Jean");
        utilisateurMock.setRole(role);

        return utilisateurMock;
    }

    List<Utilisateur> utilisateursMock(String role) throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1);
        utilisateur1.setNom("Dupont");
        utilisateur1.setPrenom("Jean");
        utilisateur1.setRole(role);

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setId(2);
        utilisateur2.setNom("Dupont");
        utilisateur2.setPrenom("Jeanne");
        utilisateur2.setRole(role);

        utilisateurs.add(utilisateur1);
        utilisateurs.add(utilisateur2);
        return utilisateurs;
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetASuperAdmin() throws Exception {
        Utilisateur utilisateurMock = this.utilisateurMock("SUPERADMIN");
    
        Mockito.when(utilisateurService.findOneByIdAndRole(1, "SUPERADMIN")).thenReturn(utilisateurMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/super-admin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dupont"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("SUPERADMIN"));

    }


    @Test
    void itShouldNotGetASuperAdmin() throws Exception {
  
        mockMvc.perform(MockMvcRequestBuilders.get("/api/super-admin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetAnAdmin() throws Exception {
        Utilisateur utilisateurMock = this.utilisateurMock("ADMIN");
    
        Mockito.when(utilisateurService.findOneByIdAndRole(1, "ADMIN")).thenReturn(utilisateurMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dupont"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("ADMIN"));
    }


    @Test
    void itShouldNotGetAnAdmin() throws Exception {
  
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "ADMIN")
    void itShouldGetAMedecin() throws Exception {
        Utilisateur utilisateurMock = this.utilisateurMock("MEDECIN");
    
        Mockito.when(utilisateurService.findOneByIdAndRole(1, "MEDECIN")).thenReturn(utilisateurMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medecin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Dupont"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.role").value("MEDECIN"));
    }


    @Test
    void itShouldNotGetAMedecin() throws Exception {
  
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medecin/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetAllSuperAdmin() throws Exception {
        List<Utilisateur> utilisateursMock = utilisateursMock("SUPERADMIN");
    
        Mockito.when(utilisateurService.findAllByNomAndRole(ArgumentMatchers.any(),ArgumentMatchers.eq("SUPERADMIN"))).thenReturn(utilisateursMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/super-admins").contentType(MediaType.APPLICATION_JSON).param("nom", ""))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("SUPERADMIN"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom").value("Jeanne"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].role").value("SUPERADMIN"));

    }

    @Test
    void itShouldNotGetAllSuperAdmins() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/super-admins").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldGetAllAdmin() throws Exception {
        List<Utilisateur> utilisateursMock = utilisateursMock("ADMIN");
    
        Mockito.when(utilisateurService.findAllByNomAndRole(ArgumentMatchers.any(),ArgumentMatchers.eq("ADMIN"))).thenReturn(utilisateursMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/admins").contentType(MediaType.APPLICATION_JSON).param("nom", ""))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("ADMIN"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom").value("Jeanne"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].role").value("ADMIN"));

    }

    @Test
    void itShouldNotGetAllAdmins() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admins").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "ADMIN")
    void itShouldGetAllMedecins() throws Exception {
        List<Utilisateur> utilisateursMock = utilisateursMock("MEDECIN");
    
        Mockito.when(utilisateurService.findAllByNomAndRole(ArgumentMatchers.any(),ArgumentMatchers.eq("MEDECIN"))).thenReturn(utilisateursMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medecins").contentType(MediaType.APPLICATION_JSON).param("nom", ""))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value("Jean"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].role").value("MEDECIN"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].prenom").value("Jeanne"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].role").value("MEDECIN"));

    }

    @Test
    void itShouldNotGetAllMedecins() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/medecins").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldAddASuperAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/super-admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "SUPERADMIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).create(utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Aurelien");
        Assertions.assertThat(utilisateurCaptor.getValue().getNom()).isEqualTo("Test");
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("SUPERADMIN");
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldAddAnAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "ADMIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).create(utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Aurelien");
        Assertions.assertThat(utilisateurCaptor.getValue().getNom()).isEqualTo("Test");
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("ADMIN");
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "ADMIN")
    void itShouldAddAMedecin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/medecin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "MEDECIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).create(utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Aurelien");
        Assertions.assertThat(utilisateurCaptor.getValue().getNom()).isEqualTo("Test");
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("MEDECIN");
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotAddASuperAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "SUPERADMIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/medecin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "SUPERADMIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotAddAnAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/super-admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "ADMIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/medecin").content("""
            {
                "prenom": "Aurelien",
                "nom": "Test",
                "role": "ADMIN" 
            }
                """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
}

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotAddAMedecin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/super-admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "MEDECIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "MEDECIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void itShouldNotAddAnyUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/medecin").content("""
                {
                   "prenom": "Aurelien",
                   "nom": "Test",
                   "role": "MEDECIN" 
                }
                """).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/admin").content("""
            {
                "prenom": "Aurelien",
                "nom": "Test",
                "role": "ADMIN" 
            }
            """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.status().isForbidden());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/super-admin").content("""
            {
                "prenom": "Aurelien",
                "nom": "Test",
                "role": "SUPERADMIN" 
            }
            """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError())
            .andExpect(MockMvcResultMatchers.status().isForbidden());
        
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldUpdateASuperAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/super-admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "SUPERADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).update(eq(1), utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Claude");
        Assertions.assertThat(utilisateurCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("SUPERADMIN");

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldUpdateAnAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "ADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).update(eq(1), utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Claude");
        Assertions.assertThat(utilisateurCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("ADMIN");

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldUpdateAMedecin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/medecin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "MEDECIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).update(eq(1), utilisateurCaptor.capture());
        Assertions.assertThat(utilisateurCaptor.getValue().getPrenom()).isEqualTo("Claude");
        Assertions.assertThat(utilisateurCaptor.getValue().getId()).isEqualTo(1);
        Assertions.assertThat(utilisateurCaptor.getValue().getRole()).isEqualTo("MEDECIN");
    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotUpdateASuperAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "SUPERADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/medecin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "SUPERADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotUpdateAnAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/super-admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "ADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/medecin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "ADMIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldNotUpdateAMedecin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/super-admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "MEDECIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "MEDECIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    void itShouldNotUpdateAnyUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/medecin/1").content("""
                {   
                    "id": 1,
                    "prenom": "Claude",
                    "role": "MEDECIN"
                }
                """).contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isForbidden());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/admin/1").content("""
            {   
                "id": 1,
                "prenom": "Claude",
                "role": "ADMIN"
            }
            """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isForbidden());

        mockMvc.perform(MockMvcRequestBuilders.put("/api/super-admin/1").content("""
            {   
                "id": 1,
                "prenom": "Claude",
                "role": "SUPERADMIN"
            }
            """).contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "SUPERADMIN")
    void itShouldDeleteAnUtilisateur() throws Exception {
        Utilisateur utilisateurMock = utilisateurMock("SUPERADMIN");
    
        Mockito.when(utilisateurService.findOneByIdAndRole(1, "SUPERADMIN")).thenReturn(utilisateurMock);
        
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/utilisateur/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(utilisateurService).delete(eq(1));
    }


    @Test
    void itShouldNotDeleteAnUtilisateur() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/utilisateur/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

    
}
