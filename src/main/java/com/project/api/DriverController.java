package com.project.api;

import com.project.entity.*;
import com.project.exception.ResourceNotFoundException;
import com.project.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@CrossOrigin("http://localhost:3000")
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @GetMapping(value = "/driver")
    public Iterable<Driver> findAll() {
        return driverRepository.findAll();
    }

    @GetMapping("/driver/{driverLicenseNo}")
    public ResponseEntity<Driver> getDriverById(@PathVariable String driverLicenseNo) {
        Driver driver = driverRepository.findById(driverLicenseNo)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id : " + driverLicenseNo));
        return ResponseEntity.ok(driver);
    }

    @PostMapping(value = "/driver")
    public Driver saveNewDriver(@RequestBody Driver driver) {
        System.out.println(driver.getBirthday());
        return  driverRepository.save(driver);
    }
    @DeleteMapping("/driver/{driverLicenseNo}")
    public ResponseEntity<Map<String, Boolean>> deleteDriver(@PathVariable String driverLicenseNo) {
        Driver driverDelete = driverRepository.findById(driverLicenseNo)
                .orElseThrow(() -> new ResourceNotFoundException("driver not exist with id : " + driverLicenseNo));

        driverRepository.delete(driverDelete);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted", Boolean.TRUE);

        return ResponseEntity.ok(res);
    }

        @PutMapping("/driver/{driverLicenseNo}")
    public ResponseEntity<Driver> updateDriver(@PathVariable String driverLicenseNo , @RequestBody Driver driverDetail){
        Driver driverUpdate = driverRepository.findById(driverLicenseNo)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id : " + driverLicenseNo));

        driverUpdate.setName(driverDetail.getName());
        driverUpdate.setId(driverDetail.getId());
        driverUpdate.setAddress(driverDetail.getAddress());
        driverUpdate.setBirthday(driverDetail.getBirthday());
        driverUpdate.setExperience(driverDetail.getExperience());

        Driver driver = driverRepository.save(driverUpdate);
        return ResponseEntity.ok(driver);
    }

    @PostMapping(value = "/driver/salary", consumes = "application/json", produces = "application/json")
    public ArrayList<DriverSalary> getSalary(@RequestBody String time) {
        List<IDriverQuery> list;
        time = time.replace("{", "");
        time = time.replace("}", "");
        time = time.replace(" ", "");
        time = time.replace("\n", "");

        String[] temp = time.split(":");
        time = temp[1].replace("\"", "");
        Calendar calendar = Calendar.getInstance();
        Date chosenDate = Date.valueOf(time.substring(0,10));
        Date currentDate = new Date(calendar.getTime().getTime());

        calendar.setTime(currentDate);
        int curMonth = calendar.get(Calendar.MONTH) + 1;
        int curYear = calendar.get(Calendar.YEAR);
        calendar.setTime(chosenDate);
        int chosenM = calendar.get(Calendar.MONTH) + 1;
        int chosenYear = calendar.get(Calendar.YEAR) ;
        Timestamp startTime;
        Timestamp endTime;
        String tempStr = "";


        if ((curMonth > chosenM && chosenYear == curYear) || chosenYear < curYear){
            tempStr = tempStr + chosenYear + "-" + chosenM + "-" + "01 00:00:00";
            startTime = Timestamp.valueOf(tempStr);
            calendar.setTime(startTime);
            int tempDay = calendar.getActualMaximum(Calendar.DATE);
            tempStr = "";
            tempStr = tempStr + chosenYear + "-" + chosenM + "-" + tempDay + " 23:59:59";
            endTime = Timestamp.valueOf(tempStr);
        }else{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            endTime = Timestamp.valueOf(formatter.format(calendar.getTime()));
            tempStr = tempStr + chosenYear + "-" + chosenM + "-" + "01 00:00:00";
            startTime = Timestamp.valueOf(tempStr);
        }
        list = driverRepository.getDriverWage(startTime, endTime);
        ArrayList<DriverSalary> driverSalaries = new ArrayList<>();


        if (!list.isEmpty()){
            boolean check1 = true;
            boolean check2 = true;
            for (IDriverQuery i: list){
                if (driverSalaries.isEmpty()){
                    DriverSalary driverSalary = new DriverSalary();
                    driverSalary.setDriverNo(i.getDriverNo());
                    driverSalary.setSalary(i.getWage()*2);
                    driverSalaries.add(driverSalary);
                    DriverSalary temp1 = new DriverSalary();
                    temp1.setDriverNo(i.getSubDriverNo());
                    temp1.setSalary(i.getWage());
                    driverSalaries.add(temp1);
                }else{
                    for (DriverSalary drive: driverSalaries){
                        if(drive.getDriverNo().equals(i.getDriverNo())) {
                            drive.setSalary(drive.getSalary() + i.getWage() * 2);
                            check1 = false;
                        }
                        if(drive.getDriverNo().equals(i.getSubDriverNo())) {
                            drive.setSalary(drive.getSalary() + i.getWage());
                            check2 = false;
                        }
                    }
                    if (check1) {
                        DriverSalary driverSalary = new DriverSalary();
                        driverSalary.setDriverNo(i.getDriverNo());
                        driverSalary.setSalary(i.getWage() * 2);
                        driverSalaries.add(driverSalary);

                    }
                    if (check2){
                        DriverSalary driverSalary = new DriverSalary();
                        driverSalary.setDriverNo(i.getSubDriverNo());
                        driverSalary.setSalary(i.getWage());
                        driverSalaries.add(driverSalary);

                    }
                    check1 = true;
                    check2 = true;
                }
            }
        }else {
            return null;
        }
        Iterable<Driver> drivers = driverRepository.findAll();
        Iterator<Driver> driverIterator = drivers.iterator();
        while(driverIterator.hasNext()){
            Driver driver = driverIterator.next();
            for(DriverSalary driverSalary1: driverSalaries){
                if (driver.getDriverLicenseNo().equals(driverSalary1.getDriverNo())){
                    driverSalary1.setDriverName(driver.getName());
                    break;
                }
            }
        }
        return driverSalaries;
    }
}
