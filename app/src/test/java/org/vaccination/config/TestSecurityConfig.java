package org.vaccination.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.vaccination.repositories.UtilisateurRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TestSecurityConfig {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "ADMIN")
    public void itShouldAllowUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    @WithMockUser(username = "toto", password = "tata", roles = "USER")
    public void itShouldNotAllowUser() throws Exception {
        // /api/patients est reservé aux rôles medecin et supérieur
        mockMvc.perform(MockMvcRequestBuilders.get("/api/patients").contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
        .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

}
