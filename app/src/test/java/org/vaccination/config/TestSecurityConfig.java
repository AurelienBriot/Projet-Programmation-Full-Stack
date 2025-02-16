package org.vaccination.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.vaccination.entities.Utilisateur;
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
    public void itShouldAllowUser() throws Exception {
        //Given
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setLogin("toto");
        utilisateur.setPassword(passwordEncoder.encode("tata"));
        utilisateurRepository.save(utilisateur);
        //when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/centres")
                        .contentType(MediaType.APPLICATION_JSON)
                        // toto:tata
                        .header("Authorization","Basic dG90bzp0YXRh"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        
        //then
    }
}
