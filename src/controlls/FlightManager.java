/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import models.Crew;
import models.Flight;
import models.Passenger;
import models.Seats;
import models.TheObject;

/**
 *
 *@author BAO TRAN
 */
public class FlightManager {


    TheObject t = new TheObject();
    TheObject o = TheObject.loadObjectFromFile();
    List<Flight> flightList = o.getFlightList();
    HashMap<String, Crew> crewMap = new HashMap<>();

    public void addFlight() throws IOException {
        int getchoice = 9; 

        do {

            String ID = "";

            do {
                ID = Utilities.getString("Enter flightNumber (format: Fxxxx) : ", 1, 0, 100);
                if (checkDuplicate(ID, 1)) {
                    System.out.println("Flight's ID cannot be duplicate. Please enter another one !");
                }
                if (!checkVehicleID(ID)) {
                    System.out.println("flightNumber has form Fxxxx (0 <= x < 10) !");
                }
            } while (checkDuplicate(ID, 1) || !checkVehicleID(ID)); 

            String departureCity = Utilities.getString("Enter departureCity: ", 1, 0, 100);
            String destinationCity = Utilities.getString("Enter destinationCity: ", 1, 0, 100);
            String departureTime = Utilities.inputDate("Enter departureTime: ", 0);
            int duration = Utilities.getInt("Enter duration (type int) ", 0, 2000000);
            String gate = Utilities.getString("Enter Gate: ", 1, 0, 100);
            List<Seats> seat = loadRegistration();
            seat.add(loadSeat(ID));
            SaveSeats(seat);
            Flight f = new Flight(ID, departureCity, destinationCity, departureTime, gate, duration);
            flightList = o.getFlightList();
            flightList.add(f);
            o.setFlightList(flightList);
            o.saveObject(o, "src//output//object.dat");

            getchoice = Utilities.getInt("Do you want to add more flight ? (yes: 1 , no: 0)", 0, 2);
            if (getchoice == 0) {
                System.out.println("----------Back to main menu!!!-------------");
                break;
            }
        } while (getchoice != 0); 
    }

    public void checkExists(String s, int opt) {
        if (opt == 1) {
            if (checkDuplicate(s, 1) == true) {
                System.out.println("Found!!!");
                for (Flight car : flightList) {

                    if (car.getFlightNumber().equalsIgnoreCase(s)) {
                        System.out.println(car.toString());
                    }
                }
            } else {
                System.out.println("No Flight Found!");

            }
        }

    }

    public void checkinSeat() {
        System.out.println("Check-in: ");
        System.out.println("Please provide your Passenger ID: ");
        Flight f = null;
        String ID = "";
        do {
            ID = Utilities.getString("Enter PassID (format: Pxxxxxx) : ", 1, 0, 100);
            if (!checkDuplicate1(ID, 1)) {
                System.out.println(" ID cannot be duplicate. Please enter another one !");
            }
            if (!checkVehicleID1(ID)) {
                System.out.println("flightNumber has form Fxxxx (0 <= x < 10) !");
            }
        } while (!checkDuplicate1(ID, 1) || !checkVehicleID1(ID)); 
        List<Passenger> ptList = loadPassengerFromFile();
        String s = "";

        for (Passenger passenger : ptList) {
            for (Flight flight : flightList) {
                if (passenger.getPassengerID().equalsIgnoreCase(ID) && passenger.getFlightID().equalsIgnoreCase(flight.getFlightNumber())) {
                    System.out.println("--------------------------------------------------------");
                    System.out.println(ID + "| booked: " + flight.toString());
                    System.out.println("--------------------------------------------------------");

                    s = flight.getFlightNumber();
                    f = flight; 
                }
            }
        }
        System.out.println("");
        System.out.println("List of seats");
        List<Seats> sList = loadRegistration();
        for (Seats seats : sList) {
            if (s.equalsIgnoreCase(seats.getFlightNumber())) {
                seats.print();

            }
        }
        String choice = "";
        do {
            choice = Utilities.getString("Please select an available seat: ", 1, 0, 1000);
        } while (!checkSeat(choice));

        Passenger p = null;
        Passenger a = null;
        ptList = loadPassengerFromFile();

        for (Passenger passenger : ptList) {
            if (passenger.getPassengerID().equalsIgnoreCase(ID)) {
                p = passenger;
                a = passenger;
                p.setSeats(choice);

                System.out.println("Congratulations! You have successfully made a reservation. Thank you!!!");
            }
        }

        Seats.choseYourSeat(choice, s);

        ptList.remove(a);
        ptList.add(p);
        SavePassenger(ptList);
        System.out.println("Your Booking Information!!!: ");

    }



    public void SavePassenger(List<Passenger> passengerList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/output/passenger.dat"))) {
            outputStream.writeObject(passengerList);
            System.out.println("Successfully saved passengers!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save passengers.");
        }
    }

    public static void SaveSeats(List<Seats> seatList) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/output/seat.dat"))) {
            outputStream.writeObject(seatList);
            System.out.println("Successfully saved seats!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save seats list.");
        }
    }

    public List<Passenger> loadPassengerFromFile() {
        List<Passenger> passengerList = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/output/passenger.dat"))) {
            passengerList = (List<Passenger>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load passenger list.");
        }
        return passengerList;
    }


    public List<Seats> loadRegistration() {
        List<Seats> seatList = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/output/seat.dat"))) {
            seatList = (List<Seats>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load seat list.");
        }
        return seatList;
    }

    public boolean checkDuplicate(String s, int opt) {

        if (opt == 1) {
            for (Flight c : flightList) {  
                if (c.getFlightNumber().equalsIgnoreCase(s)) {
                    return true;
                }
            }
        }
        if (opt == 2) {

        }

        return false;
    }

    public static boolean checkVehicleID(String str) { 
        if (str.length() != 5) {
            return false;
        }

        if (str.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
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

    public boolean checkDuplicate1(String s, int opt) {
        List<Passenger> ptList = loadPassengerFromFile();
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

    public String generatePassengerID() throws Exception {
        List<Passenger> orders = loadPassengerFromFile();
        int orderCount = orders.size();
        return "P" + String.format("%06d", orderCount);
    }

    public static void SaveCrew(HashMap<String, Crew> crewMap) {
        ArrayList<Crew> productList = new ArrayList<>(crewMap.values());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/output/crew.txt"))) {
            for (Crew car : productList) {
                writer.write(car.toString());
                writer.newLine();
            }
            System.out.println("Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save flight list.");
        }
    }

    public void update() {
        System.out.println(flightList + "1");

        int choice;
        String id;
        do {
            id = Utilities.getString("Enter Flight's ID", 1, 0, 100);
            if (checkDuplicate(id, 1) == false) {
                System.out.println("Flight ID does not exist!!!");

            }
        } while (checkDuplicate(id, 1) == false);
        for (Flight f : flightList) {
            if (f.getFlightNumber().equalsIgnoreCase(id)) { 
                do {
                    Menu menu = new Menu("---------------Update Flight's information menu--------------");
                    menu.addNewOptiont("1. Update Flight's departureCity");
                    menu.addNewOptiont("2. Update Flight's destinationCity");
                    menu.addNewOptiont("3. Update Flight's departureTime");
                    menu.addNewOptiont("4. Quit");
                    menu.printMenu();

                    choice = menu.getChoice();

                    if (choice == 1) {
                        String name = Utilities.getString("Enter Flight's departureCity: ", 1, 0, 100);
                        f.setDepartureCity(name);
                        System.out.println("Successs !!!");
                    }
                    if (choice == 2) {
                        String color;
                        color = Utilities.getString("Enter vehicle's destinationCity: ", 1, 0, 100);
                        f.setDestinationCity(color);
                        System.out.println("Successs !!!");
                    }

                    if (choice == 3) {
                        String brand = Utilities.getString("Enter new departureTime", 1, 1, 1000);
                        f.setDepartureTime(brand);
                        System.out.println("Successs !!!");
                    }

                    if (choice == 4) {
                        break;
                    }
                } while (choice != 4);

                System.out.println("---------Information after update!!!----------");
                System.out.println("|  " + f.toString() + "  |");
            }

        }
        o.setFlightList(flightList);

    }

    public void displayDescendingByDate(TheObject t) {
        List<Flight> fList = t.getFlightList();

        Collections.sort(fList, new Comparator<Flight>() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            @Override
            public int compare(Flight flight1, Flight flight2) {
                try {
                    Date date1 = sdf.parse(flight1.getDepartureTime());
                    Date date2 = sdf.parse(flight2.getDepartureTime());

                    return date2.compareTo(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        for (Flight flight : fList) {
            System.out.println(flight);
        }
    }

    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.parse(dateString);
    }

    public static Seats loadSeat(String flightID) {
        List<Seats> seatList = new ArrayList<>();
        Seats seats = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("file\\originalSeats.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                seats = new Seats(flightID, parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim(),
                        parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim(),
                        parts[8].trim(), parts[9].trim(), parts[10].trim(), parts[11].trim(),
                        parts[12].trim(), parts[13].trim(), parts[14].trim(), parts[15].trim(),
                        parts[16].trim(), parts[17].trim(), parts[18].trim(), parts[19].trim(),
                        parts[20].trim(), parts[21].trim(), parts[22].trim(), parts[23].trim());
                seatList.add(seats);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seats;
    }

    

    public void manageFlightSchedule() {
        System.out.println("1.Search Flight");
        System.out.println("2.Delete Flight");
        System.out.println("3.Update Flight");
        int choice = Utilities.getInt("Choose 1 to 3", 0, 4);
        if (choice == 1) {
            boolean found = false;
            System.out.println("--------Search a Flight Schedule---------");
            String go = Utilities.getString("Enter Destination City: ", 1, 0, 10000);
            String to = Utilities.getString("Enter Daparture City: ", 1, 0, 10000);
            System.out.println("Available flights based on your output: ");
            for (Flight f : flightList) {
                if (f.getDepartureCity().equalsIgnoreCase(go) && f.getDestinationCity().equalsIgnoreCase(to)) {

                    System.out.println("---------------------------");
                    System.out.println(". " + f.toString());
                    System.out.println("---------------------------");
                    found = true;

                }
            }
            if (!found) {
                System.out.println("No Flight Found!!!");
                return;
            }
        }

        if (choice == 2) {
            System.out.println("--------Delete a Flight Schedule---------");
            String ID = "";

            do {
                ID = Utilities.getString("Enter flightNumber (format: Fxxxx) : ", 1, 0, 100);
                if (!checkDuplicate(ID, 1)) {
                    System.out.println("Flight's ID cannot be duplicate. Please enter another one !");
                }
                if (!checkVehicleID(ID)) {
                    System.out.println("flightNumber has form Fxxxx (0 <= x < 10) !");
                }
            } while (!checkDuplicate(ID, 1) || !checkVehicleID(ID)); 

            Iterator<Flight> iterator = flightList.iterator();
            while (iterator.hasNext()) {
                Flight flight = iterator.next();
                if (flight.getFlightNumber().equalsIgnoreCase(ID)) {
                    iterator.remove(); 
                }
            }

            o.setFlightList(flightList);
            o.saveObject(o, "src//output//object.dat");
        }

        if (choice == 3) {
            update();
        }
    }

    public void saveAllData() {

    }

    public List<Flight> getList() {
        return flightList;
    }

    public TheObject getObject() {
        return o;
    }

}
