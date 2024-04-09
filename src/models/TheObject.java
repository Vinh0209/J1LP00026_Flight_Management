/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BAO TRAN
 */
public class TheObject implements Serializable {

    public List<Flight> flightList;
    public List<Schedule> listSchedule;
    public List<Reservation> r1;

    public TheObject(List<Flight> flightList, List<Schedule> listSchedule, List<Reservation> r1) {
        this.flightList = flightList;
        this.listSchedule = listSchedule;
        this.r1 = r1;
    }

    public TheObject() {
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public List<Schedule> getListSchedule() {
        return listSchedule;
    }

    public List<Reservation> getR1() {
        return r1;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public void setListSchedule(List<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public void setR1(List<Reservation> r1) {
        this.r1 = r1;
    }

    @Override
    public String toString() {
        return "Object{" + "flightList=" + flightList + ", listSchedule=" + listSchedule + ", r1=" + r1 + '}';
    }

    public static void saveObject(Object obj, String filename) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TheObject loadObjectFromFile() {
        TheObject loadedObject = null;

        try (FileInputStream fileIn = new FileInputStream("src//output//object.dat");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            loadedObject = (TheObject) objectIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load object ");
        }

        return loadedObject;
    }

}
