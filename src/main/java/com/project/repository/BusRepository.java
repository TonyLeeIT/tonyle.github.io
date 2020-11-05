package com.project.repository;

import com.project.entity.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends CrudRepository<Bus, String>{
    @Query(value = "SELECT bus.bus_plate_number as busPlateNumber, A.fare*A.guest_number as revenue from\n" +
            "(SELECT driving.bus_plate_number, driving.trip_no, trip.guest_number, trip.fare from \n" +
            "(driving\n" +
            "inner join trip)\n" +
            "where driving.trip_no = trip.trip_no) as A \n" +
            "inner join bus \n" +
            "where bus.bus_plate_number = A.bus_plate_number\n", nativeQuery = true)
    List<IBusRevenue> findBusRevenue();

    @Query(value = "select bus_plate_number as busPlateNumber, color from bus ", nativeQuery = true)
    List<ITest> testFunc();

    @Query(value = "select bus_plate_number as busPlateNumber, seat_no as seatNo, model from bus where day_left > 0", nativeQuery = true)
    List<IBusBrief> getBusBrief();

    @Query(value = "select C.road_length as roadLength, driving.bus_plate_number as busPlateNumber from \n" +
            "( driving inner join\n" +
            "(select B.trip_no, B.route_no, route.road_length from (route inner join\n" +
            "(select triproute.trip_no, triproute.route_no from triproute\n" +
            "inner join \n" +
            "(select trip_no from trip WHERE (time_destination between concat(curdate(), ' 00:00:00') AND \n" +
            "concat(curdate(), ' 23:59:59')))as A \n" +
            "where A.trip_no = triproute.trip_no)as B) where route.route_no = B.route_no)as C)where C.trip_no = driving.trip_no", nativeQuery = true)
    List<IBusRoadLength> getBusRoadLength();

    @Query(value = "update bus set day_left = day_left - ? where bus_plate_number = ?", nativeQuery = true)
    void updateDayLeft(int subtractDay, String busPlateNumber);

}
