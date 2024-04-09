/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BAO TRAN
 */
public class Flight implements Serializable {

    private String flightNumber;
    private String departureCity;
    private String destinationCity;
    private String departureTime;
    private String Gate;
    private int duration;
    List<Seats> seat;

    public Flight(String flightNumber, String departureCity, String destinationCity, String departureTime, String Gate, int duration) {
        this.flightNumber = flightNumber;
        this.departureCity = departureCity;
        this.destinationCity = destinationCity;
        this.departureTime = departureTime;
        this.Gate = Gate;
        this.duration = duration;

    }

    public void setGate(String Gate) {
        this.Gate = Gate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setSeat(List<Seats> seat) {
        this.seat = seat;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        if (flightNumber.matches("F\\d{4}")) {
            this.flightNumber = flightNumber;
        } else {
            throw new IllegalArgumentException("Invalid flight number format. It should be like F0001.");
        }
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getGate() {
        return Gate;
    }

    public int getDuration() {
        return duration;
    }

    public List<Seats> getSeat() {
        return seat;
    }

    public String toType() {
        return "Flight{" + "flightNumber=" + flightNumber + ", departureCity=" + departureCity + ", destinationCity=" + destinationCity + ", departureTime=" + departureTime + ", Gate=" + Gate + ", duration=" + duration;
    }

    @Override
    public String toString() {
        return flightNumber + ", " + departureCity + ", " + destinationCity + ", " + departureTime + ", " + Gate + ", "
                + duration;
    }

}
