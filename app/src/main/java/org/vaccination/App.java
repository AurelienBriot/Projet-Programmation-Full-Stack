/*
 * This source file was generated by the Gradle 'init' task
 */
package org.vaccination;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.vaccination.entities.Utilisateur;
import org.vaccination.repositories.UtilisateurRepository;

@SpringBootApplication
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // Ajouter un utilisateur admin dans la base
    @Bean
        CommandLineRunner init (UtilisateurRepository utilisateurRepository){
            return args -> {
                Utilisateur admin = new Utilisateur();
                admin.setLogin("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
                admin.setRole("ADMIN");
                utilisateurRepository.save(admin);
            };
        }

}
