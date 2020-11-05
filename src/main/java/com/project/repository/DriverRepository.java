package com.project.repository;

import com.project.entity.Driver;
import com.project.entity.IDriverQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {
    @Query(value = "select B.driver_no as driverNo, B.sub_driver_no as subDriverNo, sum(route.road_length * route.complexity/2) as wage\n" +
            "from(select A.driver_no, A.sub_driver_no, A.trip_no, A.route_no, trip.time_destination \n" +
            "from(select driving.driver_no, driving.sub_driver_no, driving.trip_no, triproute.route_no \n" +
            "from triproute inner join driving on driving.trip_no = triproute.trip_no)as A \n" +
            "inner join trip on A.trip_no = trip.trip_no)as B inner join route on route.route_no = B.route_no\n" +
            "where B.time_destination >= ? and B.time_destination <= ? group by B.driver_no, B.sub_driver_no", nativeQuery = true)
    public List<IDriverQuery> getDriverWage(Timestamp startTime, Timestamp endTime);
}
