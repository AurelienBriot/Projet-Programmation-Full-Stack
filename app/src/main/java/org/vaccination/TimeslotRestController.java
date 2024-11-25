package org.vaccination;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
