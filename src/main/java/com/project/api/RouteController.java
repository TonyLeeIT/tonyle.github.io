package com.project.api;

import com.project.entity.Bus;
import com.project.entity.Driver;
import com.project.entity.Route;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class RouteController {
    @Autowired
    private RouteRepository routeRepository;

    @GetMapping(value = "/route")
    public Iterable<Route> findAll(){
        return routeRepository.findAll();
    }

    @GetMapping("/route/{routeNo}")
    public ResponseEntity<Route> getRouteById(@PathVariable String routeNo) {
        Route route = routeRepository.findById(routeNo)
                .orElseThrow(() -> new ResourceNotFoundException("Route not exist with id : " + routeNo));
        return ResponseEntity.ok(route);
    }

    @PostMapping(value = "/route")
    public Route saveNewRoute(@RequestBody Route route){
       return routeRepository.save(route);
    }

    @DeleteMapping("/route/{routeNo}")
    public ResponseEntity<Map<String , Boolean>> deleteRoute(@PathVariable String routeNo){
        Route routeDelete = routeRepository.findById(routeNo)
                .orElseThrow(() -> new ResourceNotFoundException("route not exist with id : " + routeNo));

        routeRepository.delete(routeDelete);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/route/{routeNo}")
    public ResponseEntity<Route> updateRoute(@PathVariable String routeNo , @RequestBody Route routeDetail){
        Route routeUpdate = routeRepository.findById(routeNo)
                .orElseThrow(() -> new ResourceNotFoundException("Route not exist with id : " + routeNo));

        routeUpdate.setOrigin(routeDetail.getOrigin());
        routeUpdate.setDestination(routeDetail.getDestination());
        routeUpdate.setRoadLength(routeDetail.getRoadLength());
        routeUpdate.setComplexity(routeDetail.getComplexity());
        routeUpdate.setTripRoute(routeDetail.getTripRoute());

        Route route = routeRepository.save(routeUpdate);
        return ResponseEntity.ok(route);
    }

}
