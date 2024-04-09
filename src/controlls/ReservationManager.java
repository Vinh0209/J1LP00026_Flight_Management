/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Crew;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.Seats;
import models.TheObject;

/**
 *
 * @author BAO TRAN
 */
public class ReservationManager {

    TheObject t = new TheObject();
    TheObject o = TheObject.loadObjectFromFile();
    FlightManager fm = new FlightManager();
    List<Flight> flightList = o.getFlightList();

    List<Passenger> ptList = fm.loadPassengerFromFile();
    List<Passenger> pList = new ArrayList<>();
    List<String> list = new ArrayList<>();
    public List<Reservation> rList = o.getR1();
    HashMap<String, Crew> crewMap = new HashMap<>();
    int getChoice = -9;

    public void Reservation() throws IOException, Exception {

        boolean found = false;
        int count = 0;
        Flight x = null;
        String reservationID = generateReservationID();
        System.out.println("ReservationID: " + reservationID);
        String fID = "";
        if (1 == 1) {

            String s = "";
            do {
                s = Utilities.getString("Do you want to reservations flight: (yes/no) ", 1, 0, 100000);
            } while (!s.equalsIgnoreCase("yes") && !s.equalsIgnoreCase("no"));

            if (s.equalsIgnoreCase("no")) {
                return;
            }

            System.out.println("<-------------Passenger Reservations and Booking: -------------->");
            System.out.println("Please provide your information: ");

            String ID = "";

            do {
                ID = Utilities.getString("Enter Flight ID that you chosen (format: Fxxxx) : ", 1, 0, 100);
                if (!fm.checkDuplicate(ID, 1)) {
                    System.out.println("Flight ID does not exist. Please enter another one !");
                }
                if (!FlightManager.checkVehicleID(ID)) {
                    System.out.println("Flight ID has form Fxxxx (0 <= x < 10) !");
                }
            } while (!fm.checkDuplicate(ID, 1) || !FlightManager.checkVehicleID(ID));

            for (Flight flight : flightList) {
                if (ID.equalsIgnoreCase(flight.getFlightNumber())) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println(flight.toString());
                    System.out.println("--------------------------------------------------------");

                    s = flight.getFlightNumber();
                    x = flight;
                }
            }

            do {

                String id = fm.generatePassengerID();
                System.out.println("Your PassengerID is " + id + ": ");
                String name = Utilities.getString("Enter your name: ", 1, 0, 100);
                String bd = Utilities.inputDate1("Enter Birth Day: ", 0);

                int checkin = Utilities.getInt("Dis you checked in ? (True:1 False:0): ", 0, 2);
                String FlightID = ID;
                String seats = "null";
                Passenger pass = new Passenger(id, name, bd, FlightID, seats);

                System.out.println("");
                System.out.println("List of seats " + ID);
                List<Seats> sList = fm.loadRegistration();

                for (Seats s1 : sList) {
                    if (s.equalsIgnoreCase(s1.getFlightNumber())) {
                        s1.print();

                    }
                }

                String choice = "";
                do {
                    choice = Utilities.getString("Please select an available seat: ", 1, 0, 1000);
                } while (!FlightManager.checkSeat(choice));

                Passenger a = null;

                pass.setSeats(choice);
                System.out.println("Congratulations! You have successfully made a reservation. Thank you!!!");

                Seats.choseYourSeat(choice, s);
                ptList.add(pass);
                fm.SavePassenger(ptList);

                Passenger p = pass;

                display(p.getPassengerID(), s, p.getName(), x.getDepartureCity(), x.getDepartureTime(), x.getDestinationCity(), choice);

                list.add(choice);
                pList.add(pass);

                getChoice = Utilities.getInt("Do you want to book more ticket? (Yes: 1, No: 0)", 0, 2);
                if (getChoice == 0) {
                    Reservation r = new Reservation(reservationID, pList, x.getGate(), checkin, list);
                    rList.add(r);
                    o.setR1(rList);
                    o.saveObject(o, "src//output//object.dat");
                    break;
                }

            } while (getChoice != 0);

        }
    }

    public void checkinSeat() {
        Passenger pass = null;
        System.out.println("Check-in information: ");
        System.out.println("Please provide your Passenger ID: ");
        Flight x = null;
        String ID = "";
        do {
            ID = Utilities.getString("Enter PassID (format: Pxxxxxx) : ", 1, 0, 100);
            if (!fm.checkDuplicate1(ID, 1)) {
                System.out.println(" ID cannot be duplicate. Please enter another one !");
            }
            if (!checkVehicleID1(ID)) {
                System.out.println("flightNumber has form Fxxxx (0 <= x < 10) !");
            }
        } while (!checkDuplicate1(ID, 1) || !checkVehicleID1(ID));
        List<Passenger> ptList1 = fm.loadPassengerFromFile();
        String s = "";
        System.out.println("");

        for (Passenger passenger : ptList1) {
            for (Flight flight : flightList) {
                if (passenger.getPassengerID().equalsIgnoreCase(ID) && passenger.getFlightID().equalsIgnoreCase(flight.getFlightNumber())) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println(ID + " - " + passenger.getName() + "| booked: " + flight.toString());
                    System.out.println("--------------------------------------------------------");
                    pass = passenger;
                    s = flight.getFlightNumber();
                    x = flight;
                    break;
                }
            }
        }

        System.out.println("");
        System.out.println("");
        System.out.println("List of seats");
        List<Seats> sList = fm.loadRegistration();
        for (Seats seats : sList) {
            if (s.equalsIgnoreCase(seats.getFlightNumber())) {
                seats.print();

            }
        }

        System.out.println("---------------------------------------------");
        System.out.println("Your current seat is: " + pass.getSeats());
        System.out.println("---------------------------------------------");

        String s1 = "";
        do {
            s1 = Utilities.getString("Do you want to change your seat: (yes/no) ", 1, 0, 100000);
        } while (!s1.equalsIgnoreCase("yes") && !s1.equalsIgnoreCase("no"));

        if (s1.equalsIgnoreCase("yes")) {
            String choice = "";
            do {
                choice = Utilities.getString("Please select an available seat: ", 1, 0, 1000);
            } while (!checkSeat(choice));
            Passenger p = null;
            Passenger a = null;
            ptList1 = fm.loadPassengerFromFile();

            for (Passenger passenger : ptList1) {
                if (passenger.getPassengerID().equalsIgnoreCase(ID)) {
                    p = passenger;
                    a = passenger;
                    p.setSeats(choice);

                    System.out.println("Congratulations! You have successfully made a reservation. Thank you!!!");
                }
            }

            Seats.choseYourSeat(choice, s);

            ptList1.remove(a);
            ptList1.add(p);
            fm.SavePassenger(ptList1);

            display(p.getPassengerID(), s, p.getName(), x.getDepartureCity(), x.getDepartureTime(), x.getDestinationCity(), choice);
        }
        if (s1.equalsIgnoreCase("no")) {

            display(pass.getPassengerID(), s, pass.getName(), x.getDepartureCity(), x.getDepartureTime(), x.getDestinationCity(), pass.getSeats());
        }

    }

    public String generateReservationID() throws IOException {
        List<Reservation> orders = o.getR1();
        int orderCount = orders.size();
        return "R" + String.format("%04d", orderCount);
    }

    public boolean checkDuplicate1(String s, int opt) {
        List<Passenger> ptList = fm.loadPassengerFromFile();
        if (opt == 1) {
            for (Passenger c : ptList) {
                if (c.getPassengerID().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }
        if (opt == 2) {

        }

        return false;
    }

    public static boolean checkVehicleID1(String str) {
        if (str.length() != 7) {
            return false;
        }

        if (str.charAt(0) != 'P') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private String formatString(String input, int length) {
        if (input.length() >= length) {
            return input.substring(0, length);
        } else {
            StringBuilder sb = new StringBuilder(input);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    public static boolean checkSeat(String str) {
        if (str.length() != 2) {
            return false;
        }

        if (str.charAt(0) != 'A' && str.charAt(0) != 'B' && str.charAt(0) != 'C' && str.charAt(0) != 'D' && str.charAt(0) != 'E' && str.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public List<Reservation> getList() {
        return rList;
    }

    public void display(String passengerID, String airplaneID, String name,
            String departure, String departureDateTime, String destination, String seat) {
        System.out.println("****************************************************************************************");
        System.out.println("*                           VIETNAM-AIRLINE BUSINESS TICKET                            *");
        System.out.println("****************************************************************************************");
        System.out.println("*ID Passenger:  " + formatString(passengerID, 10) + "   |****************Airplane ID:     " + formatString(airplaneID, 5) + "********************");
        System.out.println("*Name:         " + formatString(name, 10) + "   *");
        System.out.println("*Departure:    " + formatString(departure, 10) + "    |Departure Date and Time: " + formatString(departureDateTime, 32) + "*");
        System.out.println("*Destination:  " + formatString(destination, 10) + "    |Seat:                   " + formatString(seat, 3) + "                              *");
        System.out.println("****************************************************************************************");
    }
}
