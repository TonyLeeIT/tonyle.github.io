package com.project.api;

import com.project.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class ScheduleController {
    @Autowired
    TripRepository tripRepository;

    @Scheduled
    public void autoUpdateWarrantyDate(){
        tripRepository.findAll();
    }
}
