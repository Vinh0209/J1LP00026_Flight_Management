/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;


import java.util.ArrayList;
import java.util.List;
import models.Flight;
import models.Passenger;
import models.Reservation;
import models.Schedule;
import models.Staff;
import models.TheObject;

/**
 *
 * @author BAO TRAN
 */
public class CrewManager {

    FlightManager fm = new FlightManager();
    ReservationManager rm = new ReservationManager();
    TheObject t = new TheObject();
    TheObject o = TheObject.loadObjectFromFile();
    List<Flight> flightList = o.getFlightList();
    List<Passenger> ptList = fm.loadPassengerFromFile();
    List<Passenger> pList = new ArrayList<>();
    public List<Reservation> rList = o.getR1();
    List<Staff> listPilots = new ArrayList<>();
    List<Staff> listAttendants = new ArrayList<>();
    List<Staff> listGroundAttendants = new ArrayList<>();
    List<Schedule> listSchedule = o.getListSchedule();

    public void crewAssign() {

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
        System.out.println("Enter list of pilots: ");
        int choice = -8;
        do {
            String id = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff = new Staff(id, name, role);
            listPilots.add(staff);
            choice = Utilities.getInt("Do you want to add more staff? (yes:1 / no:0)", 0, 2);

        } while (choice != 0);

        System.out.println("Enter list of attendants: ");
        int choice1 = -8;
        do {
            String id1 = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name1 = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role1 = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff1 = new Staff(id1, name1, role1);
            listAttendants.add(staff1);
            choice1 = Utilities.getInt("Do you want to add more staff?(yes:1 / no:0) ", 0, 2);

        } while (choice1 != 0);
        System.out.println("-----------------------------------");
        System.out.println("Enter list of groundStaff: ");
        int choice2 = -8;
        do {
            String id2 = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name2 = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role2 = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff2 = new Staff(id2, name2, role2);
            listGroundAttendants.add(staff2);
            choice2 = Utilities.getInt("Do you want to add more staff? (yes:1 / no:0) ", 0, 2);

        } while (choice2 != 0);

        Schedule s = new Schedule(listPilots, listAttendants, listGroundAttendants, ID);
        listSchedule.add(s);

        o.setListSchedule(listSchedule);
        o.saveObject(o, "src//output//object.dat");

    }

    public void manageFlightCrew() {
        System.out.println("1.Search Flight's Staff");
        System.out.println("2.Delete Flight's Staff");
        System.out.println("3.Update Flight's Staff");
        int choice = Utilities.getInt("Choose 1 to 3", 0, 4);

        if (choice == 1) {
            System.out.println("Please provide Flight ID to search Crew: ");

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
            for (Schedule s : listSchedule) {
                if (s.getFlightID().equalsIgnoreCase(ID)) {
                    System.out.println(s.toString());
                }
            }
        }

        if (choice == 2) {
            System.out.println("Please provide Flight ID to delete Crew: ");

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

            Schedule a = null;
            for (Schedule s : listSchedule) {
                if (s.getFlightID().equalsIgnoreCase(ID)) {
                    a = s;
                }
            }

            listSchedule.remove(a);
            o.setListSchedule(listSchedule);
            o.saveObject(o, "src//output//object.dat");

        }
        if (choice == 3) {
            System.out.println("Please provide Flight ID to Update: ");

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

            for (int i = 0; i < listSchedule.size(); i++) {
                Schedule get = listSchedule.get(i);
                if (get.getFlightID().equalsIgnoreCase(ID)) {
                    listSchedule.remove(i);
                }
            }
            crewAssig1(ID);

        }

    }

    public void crewAssig1(String ID) {

        System.out.println("Enter list of pilots: ");
        int choice = -8;
        do {
            String id = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff = new Staff(id, name, role);
            listPilots.add(staff);
            choice = Utilities.getInt("Do you want to add more staff? (yes:1 / no:0)", 0, 2);

        } while (choice != 0);

        System.out.println("Enter list of attendants: ");
        int choice1 = -8;
        do {
            String id1 = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name1 = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role1 = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff1 = new Staff(id1, name1, role1);
            listAttendants.add(staff1);
            choice1 = Utilities.getInt("Do you want to add more staff?(yes:1 / no:0) ", 0, 2);

        } while (choice1 != 0);
        System.out.println("-----------------------------------");
        System.out.println("Enter list of groundStaff: ");
        int choice2 = -8;
        do {
            String id2 = Utilities.getString("Enter id: ", 1, 0, 1000);
            String name2 = Utilities.getString("Enter name: ", 1, 0, 1000);
            String role2 = Utilities.getString("Enter role: ", 1, 0, 1000);
            Staff staff2 = new Staff(id2, name2, role2);
            listGroundAttendants.add(staff2);
            choice2 = Utilities.getInt("Do you want to add more staff? (yes:1 / no:0) ", 0, 2);

        } while (choice2 != 0);

        Schedule s = new Schedule(listPilots, listAttendants, listGroundAttendants, ID);
        listSchedule.add(s);

        o.setListSchedule(listSchedule);
        o.saveObject(o, "src//output//object.dat");

    }

    public List<Schedule> getList() {
        return listSchedule;
    }

}
