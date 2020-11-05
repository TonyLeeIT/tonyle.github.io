package com.project.api;
import com.project.entity.Bus;

import com.project.entity.BusTemp;
import com.project.entity.IBusRevenue;
import com.project.entity.ITest;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/")
//@EnableScheduling
public class BusController {
    @Autowired
    private BusRepository busRepository;

    @GetMapping(value = "/bus", produces = "application/json")
    public Iterable<Bus> getAll() {
        return busRepository.findAll();
    }

    @GetMapping("/bus/{busPlateNumber}")
    public ResponseEntity<Bus> getBusById(@PathVariable String busPlateNumber) {
        Bus bus = busRepository.findById(busPlateNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not exist with id : " + busPlateNumber));
        return ResponseEntity.ok(bus);
    }

    @PostMapping("/bus")
    public Bus createBus(@RequestBody Bus bus) {
        return	busRepository.save(bus);
    }

    @DeleteMapping("/bus/{busPlateNumber}")
    public ResponseEntity<Map<String , Boolean>> deleteEmployee(@PathVariable String busPlateNumber){
        Bus busDelete = busRepository.findById(busPlateNumber)
                .orElseThrow(() -> new ResourceNotFoundException("bus not exist with id : " + busPlateNumber));

        busRepository.delete(busDelete);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(res);
    }

    @PutMapping("/bus/{busPlateNumber}")
    public ResponseEntity<Bus> updateBus(@PathVariable String busPlateNumber , @RequestBody Bus busDetail){
        Bus busUpdate = busRepository.findById(busPlateNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not exist with id : " + busPlateNumber));

        busUpdate.setColor(busDetail.getColor());
        busUpdate.setManufacturer(busDetail.getManufacturer());
        busUpdate.setLine(busDetail.getLine());
        busUpdate.setModel(busDetail.getModel());
        busUpdate.setSeatNo(busDetail.getSeatNo());
        busUpdate.setYearUsed(busDetail.getYearUsed());
        busUpdate.setLastWarrantyDate(busDetail.getLastWarrantyDate());
        busUpdate.setDayLeft(busDetail.getDayLeft());

        Bus bus = busRepository.save(busUpdate);
        return ResponseEntity.ok(bus);
    }

    @GetMapping(value = "bus/warranty", produces = "application/json")
    public List<BusTemp> warrantyDate(){
        Iterable<Bus> buses = busRepository.findAll();
        java.sql.Date date;
        List<BusTemp> list = new ArrayList<>();
        int dayLeft;
        for(Bus bus: buses){
            BusTemp warranty = new BusTemp();
            date = bus.getLastWarrantyDate();
            dayLeft = bus.getDayLeft();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, dayLeft);

            warranty.setYearUsed(bus.getYearUsed());
            warranty.setBusPlateNumber(bus.getBusPlateNumber());
            warranty.setColor(bus.getColor());
            warranty.setLine(bus.getLine());
            warranty.setDayLeft(bus.getDayLeft());
            warranty.setManufacturer(bus.getManufacturer());
            warranty.setSeatNo(bus.getSeatNo());
            warranty.setModel(bus.getModel());
            warranty.setLastWarrantyDate(bus.getLastWarrantyDate());

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = java.sql.Date.valueOf(format.format(c.getTime()));
            warranty.setNextWarrantyDate(date);

            if (dayLeft > 0){
                warranty.setCheckOutOfDate(Boolean.FALSE);
            }else warranty.setCheckOutOfDate(Boolean.TRUE);
            list.add(warranty);
        }
        return list;
    }

    @GetMapping(value = "bus/revenue", produces = "application/json")
    public List<IBusRevenue> findRevenue(){
        return busRepository.findBusRevenue();
    }

    @GetMapping(value = "bus/test", produces = "application/json")
    public List<ITest> test(){
        return busRepository.testFunc();
    }
}
