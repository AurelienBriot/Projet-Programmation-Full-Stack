package org.vaccination;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.FlashMapManager;

@Service
public class TimeslotService {
    private List<Timeslot> timeslots = new ArrayList<Timeslot>();

    public TimeslotService() { 
        timeslots.add(new Timeslot(1,  null, null, null, false, false));
    }

    public List<Timeslot> findAll() {
        return this.timeslots.stream().toList();
    }

    public Timeslot findOneById(Integer id) throws Exception {
        return this.timeslots.stream()
        .filter(t->t.getId().equals(id))
        .findFirst()
        .orElseThrow(Exception::new);
    }
}
