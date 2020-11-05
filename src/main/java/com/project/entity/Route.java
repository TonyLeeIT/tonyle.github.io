package com.project.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Route {
    String origin;
    String destination;
    float roadLength;
    String complexity;
    String tripRoute;
    @Id
    String routeNo;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public float getRoadLength() {
        return roadLength;
    }

    public void setRoadLength(float roadLength) {
        this.roadLength = roadLength;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getTripRoute() {
        return tripRoute;
    }

    public void setTripRoute(String tripRoute) {
        this.tripRoute = tripRoute;
    }

    public String getRouteNo() {
        return routeNo;
    }

    public void setRouteNo(String routeNo) {
        this.routeNo = routeNo;
    }
}

