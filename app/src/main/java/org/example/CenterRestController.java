package org.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping(path = "/public/centres")
    public void create(@RequestBody Center p) throws URISyntaxException {
        centerService.create(p);
        ResponseEntity.created(new URI("public/centre/"+p.getId())).build();
    }

    @GetMapping(path = "public/centre/{id}", produces = {"application/json"})
    public Center getOneById(@PathVariable("id") Integer id) throws Exception {
        Center test = centerService.findOneById(id);
        System.out.println(test);
        return centerService.findOneById(id);
    }

}
