package com.project.repository;

import com.project.entity.Trip;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TripRepository extends CrudRepository<Trip, String> {
    @Transactional
    @Modifying
    @Query(value = "update trip set guest_number = guest_number + :guestNumber where trip_no = :tripNo", nativeQuery = true)
    public void updateGuessNumber(@Param("guestNumber") int guestNumber, @Param("tripNo") String tripNo);

    @Query(value = "select bus.seat_no from ((select driving.trip_no, driving.bus_plate_number from (\n" +
            "trip inner join driving on trip.trip_no = driving.trip_no)) as A\n" +
            "inner join bus on bus.bus_plate_number = A.bus_plate_number) where \n" +
            "A.trip_no = ?1", nativeQuery = true)
    public int getMaximumGuest(String tripNo);
}
