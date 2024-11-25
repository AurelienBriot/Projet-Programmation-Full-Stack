package org.vaccination;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeslotRestController {

    @Autowired
    private TimeslotService timeslotService;

    public TimeslotRestController() {

    }

    @GetMapping(path = "/api/admin/creneaux", produces = {"application/json"})
    public List<Timeslot> getAll() {
        return this.timeslotService.findAll();
    }

    @GetMapping(path = "/api/admin/creneau/{id}", produces = {"application/json"})
    public Timeslot getOneById(@PathVariable("id") Integer id) throws Exception {
        return this.timeslotService.findOneById(id);
    }

}
