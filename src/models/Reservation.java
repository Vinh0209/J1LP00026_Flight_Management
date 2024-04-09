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
public class Reservation implements Serializable {

    private String reservationID;
    private String flightID;
    private List<Passenger> passenger;
    private String Gate;
    private int status;
    List<String> seat;

    public Reservation(String reservationID, List<Passenger> passenger, String Gate, int status, List<String> seat) {
        this.reservationID = reservationID;
        this.passenger = passenger;
        this.Gate = Gate;
        this.status = status;
        this.seat = seat;

    }

    public String getReservationID() {
        return reservationID;
    }

    public String getFlightID() {
        return flightID;
    }

    public List<Passenger> getPassenger() {
        return passenger;
    }

    public String getGate() {
        return Gate;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getSeat() {
        return seat;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setPassenger(List<Passenger> passenger) {
        this.passenger = passenger;
    }

    public void setGate(String Gate) {
        this.Gate = Gate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setSeat(List<String> seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return reservationID + "; " + passenger + "; " + Gate + "; " + status + "; " + seat;
    }

}
