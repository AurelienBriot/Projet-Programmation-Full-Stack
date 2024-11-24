package org.vaccination;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CenterRestController {

    @Autowired
    private CenterService centerService;
    
    public CenterRestController() {

    }

    @GetMapping(path = "/public/centres", produces = {"application/json"})
    public List<Center> getAll() throws Exception {
        return centerService.findAll();
    }

    @GetMapping(path = "/public/centres/{ville}", produces = {"application/json"})
    public List<Center> getAllByCity(@PathVariable("ville") String ville) throws Exception {
        return centerService.findAllByCity(ville);
    }


    @GetMapping(path = "public/centre/{id}", produces = {"application/json"})
    public Center getOneById(@PathVariable("id") Integer id) throws Exception {
        Center test = centerService.findOneById(id);
        System.out.println(test);
        return centerService.findOneById(id);
    }

    @PostMapping(path = "admin/centre")
    public void create(@RequestBody Center c) throws URISyntaxException {
        centerService.create(c);
        ResponseEntity.created(new URI("public/centre/"+ c.getId())).build();
    }

    @DeleteMapping(path = "/admin/centre/{id}")
    public void delete(@PathVariable("id") Integer id) {
        centerService.delete(id);
    }

    @PostMapping(path = "admin/centre/{id}/admin")
    public void updateAdmin(@PathVariable("id") Integer id, @RequestBody User user) throws URISyntaxException {
        centerService.addAdmin(id, user);
    }

    @DeleteMapping(path = "/admin/centre/{id}/admin")
    public void deleteAdmin(@PathVariable("id") Integer id) {
        centerService.deleteAdmin(id);
    }

    @PostMapping(path = "admin/centre/{id}/adresse")
    public void updateAdresse(@PathVariable("id") Integer id, @RequestBody String adresse) throws URISyntaxException {
        centerService.updateAdresse(id, adresse);
    }

    @PostMapping(path = "admin/centre/{id}/ville")
    public void updateVille(@PathVariable("id") Integer id, @RequestBody String ville) throws URISyntaxException {
        centerService.updateVille(id, ville);
    }

    @PostMapping(path = "admin/centre/{id}/medecin")
    public void addMedecin(@PathVariable("id") Integer id, @RequestBody User medecin) throws URISyntaxException {
        centerService.addMedecin(id, medecin);
    }

    @DeleteMapping(path = "admin/centre/{id}/medecin/{userId}")
    public void removeMedecin(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) throws URISyntaxException {
        centerService.removeMedecin(id, userId);
    }

}
