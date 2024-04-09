/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author BAO TRAN
 */
public class Passenger implements Serializable {

    private String PassengerID;
    private String name;
    private String BD;
    private String FlightID;
    private String seats;

    public Passenger(String PassengerID, String name, String BD, String FlightID, String seats) {
        this.seats = seats;
        this.PassengerID = PassengerID;
        this.name = name;
        this.BD = BD;
        this.FlightID = FlightID;
    }

    public String getPassengerID() {
        return PassengerID;
    }

    public String getName() {
        return name;
    }

    public String getBD() {
        return BD;
    }

    public void setPassengerID(String PassengerID) {
        this.PassengerID = PassengerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    @Override
    public String toString() {
        return PassengerID + ": " + name + ": " + BD + ": " + FlightID + ": " + seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getSeats() {
        return seats;
    }

    public void setFlightID(String FlightID) {
        this.FlightID = FlightID;
    }

    public String getFlightID() {
        return FlightID;
    }

}
