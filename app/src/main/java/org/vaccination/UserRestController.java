package org.vaccination;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    
    public UserRestController() {

    }

    @PostMapping(path = "/private/create")
    public void create(@RequestBody User user) {
        userService.create(user);
        //ResponseEntity.created(new URI("patient/"+p.getId())).build();
    }

    @GetMapping(path = "private/user/{id}", produces = {"application/json"})
    public User getOneById(@PathVariable("id") Integer id) throws Exception {
        User test = userService.findOneById(id);
        System.out.println(test);
        return userService.findOneById(id);
    }

}
