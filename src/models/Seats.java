/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controlls.FlightManager;
import java.util.List;
import java.io.Serializable;

/**
 *
 * @author BAO TRAN
 */
public class Seats implements Serializable {

    private String flightNumber;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String b1;
    private String b2;
    private String b3;
    private String b4;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String d1;
    private String d2;
    private String d3;
    private String d4;
    private String e1;
    private String e2;
    private String e3;
    private String e4;
    private String f1;
    private String f2;
    private String f3;
    private String f4;

    public Seats(String flightNumber, String a1, String a2, String a3, String a4, String b1, String b2, String b3, String b4, String c1, String c2, String c3, String c4, String d1, String d2, String d3, String d4, String e1, String e2, String e3, String e4, String f1, String f2, String f3, String f4) {
        this.flightNumber = flightNumber;
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
        this.b1 = b1;
        this.b2 = b2;
        this.b3 = b3;
        this.b4 = b4;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.d4 = d4;
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
        this.e4 = e4;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public String getB1() {
        return b1;
    }

    public String getB2() {
        return b2;
    }

    public String getB3() {
        return b3;
    }

    public String getB4() {
        return b4;
    }

    public String getC1() {
        return c1;
    }

    public String getC2() {
        return c2;
    }

    public String getC3() {
        return c3;
    }

    public String getC4() {
        return c4;
    }

    public String getD1() {
        return d1;
    }

    public String getD2() {
        return d2;
    }

    public String getD3() {
        return d3;
    }

    public String getD4() {
        return d4;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }

    public String getE3() {
        return e3;
    }

    public String getE4() {
        return e4;
    }

    public String getF1() {
        return f1;
    }

    public String getF2() {
        return f2;
    }

    public String getF3() {
        return f3;
    }

    public String getF4() {
        return f4;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }

    public void setB1(String b1) {
        this.b1 = b1;
    }

    public void setB2(String b2) {
        this.b2 = b2;
    }

    public void setB3(String b3) {
        this.b3 = b3;
    }

    public void setB4(String b4) {
        this.b4 = b4;
    }

    public void setC1(String c1) {
        this.c1 = c1;
    }

    public void setC2(String c2) {
        this.c2 = c2;
    }

    public void setC3(String c3) {
        this.c3 = c3;
    }

    public void setC4(String c4) {
        this.c4 = c4;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public void setD3(String d3) {
        this.d3 = d3;
    }

    public void setD4(String d4) {
        this.d4 = d4;
    }

    public void setE1(String e1) {
        this.e1 = e1;
    }

    public void setE2(String e2) {
        this.e2 = e2;
    }

    public void setE3(String e3) {
        this.e3 = e3;
    }

    public void setE4(String e4) {
        this.e4 = e4;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public void setF4(String f4) {
        this.f4 = f4;
    }

    @Override
    public String toString() {
        return flightNumber + ", " + a1 + ", " + a2 + ", " + a3 + ", " + a4 + ", " + b1 + ", " + b2
                + ", " + b3 + ", " + b4 + ", " + c1 + ", " + c2 + ", " + c3 + ", "
                + c4 + ", " + d1 + ", " + d2 + ", " + d3 + ", " + d4 + ", "
                + e1 + ", " + e2 + ", " + e3 + ", " + e4 + ", " + f1 + ", " + f2 + ", " + f3 + ", " + f4;
    }

    public void print() {
        System.out.println(" " + a1 + "  " + a2 + "  " + a3 + "  " + a4 + " ");
        System.out.println(" " + b1 + "  " + b2 + "  " + b3 + "  " + b4 + " ");
        System.out.println(" " + c1 + "  " + c2 + "  " + c3 + "  " + c4 + " ");
        System.out.println(" " + d1 + "  " + d2 + "  " + d3 + "  " + d4 + " ");
        System.out.println(" " + e1 + "  " + e2 + "  " + e3 + "  " + e4 + " ");
        System.out.println(" " + f1 + "  " + f2 + "  " + f3 + "  " + f4 + " ");

    }

    public static void choseYourSeat(String choice, String Flight) {
        FlightManager f = new FlightManager();
        List<Seats> sList1 = f.loadRegistration();
        Seats seats = null;
        for (Seats s : sList1) {
            if (Flight.equalsIgnoreCase(s.getFlightNumber())) {
                seats = s;
            }

        }
        if (choice.equalsIgnoreCase("A1")) {
            seats.setA1("Sold");
        }
        if (choice.equalsIgnoreCase("A2")) {
            seats.setA2("Sold");
        }
        if (choice.equalsIgnoreCase("A3")) {
            seats.setA3("Sold");
        }
        if (choice.equalsIgnoreCase("A4")) {
            seats.setA4("Sold");
        }
        if (choice.equalsIgnoreCase("B1")) {
            seats.setB1("Sold");
        }
        if (choice.equalsIgnoreCase("B2")) {
            seats.setB2("Sold");
        }
        if (choice.equalsIgnoreCase("B3")) {
            seats.setB3("Sold");
        }
        if (choice.equalsIgnoreCase("B4")) {
            seats.setB4("Sold");
        }
        if (choice.equalsIgnoreCase("C1")) {
            seats.setC1("Sold");
        }
        if (choice.equalsIgnoreCase("C2")) {
            seats.setC2("Sold");
        }
        if (choice.equalsIgnoreCase("C3")) {
            seats.setC3("Sold");
        }
        if (choice.equalsIgnoreCase("C4")) {
            seats.setC4(choice);
        }
        if (choice.equalsIgnoreCase("D1")) {
            seats.setD1("Sold");
        }
        if (choice.equalsIgnoreCase("D2")) {
            seats.setD2("Sold");
        }
        if (choice.equalsIgnoreCase("D3")) {
            seats.setD3("Sold");
        }
        if (choice.equalsIgnoreCase("D4")) {
            seats.setD4("Sold");
        }
        if (choice.equalsIgnoreCase("E1")) {
            seats.setE1("Sold");
        }
        if (choice.equalsIgnoreCase("E2")) {
            seats.setE2("Sold");
        }
        if (choice.equalsIgnoreCase("E3")) {
            seats.setE3("Sold");
        }
        if (choice.equalsIgnoreCase("E4")) {
            seats.setE4("Sold");
        }
        if (choice.equalsIgnoreCase("F1")) {
            seats.setF1("Sold");
        }
        if (choice.equalsIgnoreCase("F2")) {
            seats.setF2("Sold");
        }
        if (choice.equalsIgnoreCase("F3")) {
            seats.setF3("Sold");
        }
        if (choice.equalsIgnoreCase("F4")) {
            seats.setF4("Sold");
        }

        FlightManager.SaveSeats(sList1);

    }

}
