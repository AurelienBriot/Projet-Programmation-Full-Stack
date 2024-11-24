package org.vaccination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private List<User> users = new ArrayList<User>();
    
    public UserService() {
        this.users.add(new User(1, "Briot", "Aurélien", "aurelienbriot@mail.com"));

    }

    public List<User> findAll(String nom) {
        return this.users.stream()
        .filter(p->p.getNom().startsWith(nom))
        .toList();
    }

    public User findOneById(Integer id) throws Exception {
        return this.users.stream()
        .filter(p->p.getId().equals(id))
        .findFirst()
        .orElseThrow(Exception::new);    
    }

    public void create(User user) {
        this.users.add(user);
    }

    public void delete(Integer id) {
        this.users.removeIf(p->p.getId().equals(id));    
    }

}
