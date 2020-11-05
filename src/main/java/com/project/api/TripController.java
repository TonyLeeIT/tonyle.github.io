package com.project.api;

import com.project.entity.Bus;
import com.project.entity.Trip;
import com.project.entity.TripBooking;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@CrossOrigin("http://localhost:3000")
public class TripController {
    @Autowired
    private TripRepository tripRepository;

    @GetMapping(value = "/trip", produces = "application/json")
    public Iterable<Trip> findAll(){
        return tripRepository.findAll();
    }

//    @PostMapping(value = "/trip/save", consumes = "application/json")
//    public void saveNewTrip(@RequestBody Trip trip){
//        tripRepository.save(trip);
//    }
//
@DeleteMapping("/trip/{tripNo}")
public ResponseEntity<Map<String , Boolean>> deleteTrip(@PathVariable String tripNo){
    Trip tripDelete = tripRepository.findById(tripNo)
            .orElseThrow(() -> new ResourceNotFoundException("trip not exist with id : " + tripNo));

    tripRepository.delete(tripDelete);
    Map<String, Boolean> res = new HashMap<>();
    res.put("deleted", Boolean.TRUE);

    return ResponseEntity.ok(res);
}
//
//    @PostMapping(value = "/trip/{id}", consumes = "application/json")
//    public Optional<Trip> findTripById(@PathVariable("id") String id){
//        return tripRepository.findById(id);
//    }
//

    @PostMapping(value = "/trip/booking", produces = "application/json")
    public String tripBooking(@RequestBody TripBooking tripBooking){
        System.out.println(tripBooking);
        int guestNumber = tripBooking.getGuestNumber();
        String tripNo = tripBooking.getTripNo();
        System.out.println(guestNumber);
        Optional<Trip> optionalTrip = tripRepository.findById(tripNo);
        Trip trip;
        if (optionalTrip.isPresent()){
            trip = optionalTrip.get();
            System.out.println(trip);
        }else {
            return  "the plate number is not exist";
        }
        int checkGuest = tripRepository.getMaximumGuest(tripNo);
        int currentGuest = trip.getGuestNumber();
        if ((currentGuest + guestNumber) <= (checkGuest - 2)){
            tripRepository.updateGuessNumber(guestNumber, tripNo);
        }else{
            return "reach the maximum number of guest";
        }
        return "You have booked " + guestNumber + " tickets";
    }
}
