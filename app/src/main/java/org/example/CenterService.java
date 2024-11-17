package org.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CenterService {
    private List<Center> centers = new ArrayList<Center>();
    
    public CenterService() {
        this.centers.add(new Center(1, "Rue Jean Lamour", "Vandoeuvre-lès-Nancy", new ArrayList<User>(), new ArrayList<Timeslot>()));
    }

    public List<Center> findAll(String ville) {
        return this.centers.stream()
        .filter(p->p.getVille().startsWith(ville))
        .toList();
    }

    public Center findOneById(Integer id) throws Exception {
        return this.centers.stream()
        .filter(p->p.getId().equals(id))
        .findFirst()
        .orElseThrow(Exception::new);    
    }

    public void create(Center center) {
        this.centers.add(center);
    }
}
