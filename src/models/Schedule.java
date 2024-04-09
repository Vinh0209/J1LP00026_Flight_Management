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
public class Schedule implements Serializable {
   private List<Staff>pilots;
    private List<Staff>attendants;
   private  List<Staff>groundStaff;
   private String  flightID;

    public Schedule(List<Staff> pilots, List<Staff> attendants, List<Staff> groundStaff, String flightID) {
        this.pilots = pilots;
        this.attendants = attendants;
        this.groundStaff = groundStaff;
        this.flightID = flightID;
    }

    public List<Staff> getPilots() {
        return pilots;
    }

    public List<Staff> getAttendants() {
        return attendants;
    }

    public List<Staff> getGroundStaff() {
        return groundStaff;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setPilots(List<Staff> pilots) {
        this.pilots = pilots;
    }

    public void setAttendants(List<Staff> attendants) {
        this.attendants = attendants;
    }

    public void setGroundStaff(List<Staff> groundStaff) {
        this.groundStaff = groundStaff;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    @Override
    public String toString() {
        return flightID +"; "+  pilots + "; " + attendants + "; " + groundStaff ;
    }
   
   
}
