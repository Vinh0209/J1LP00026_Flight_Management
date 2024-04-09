package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controlls.FlightManager;
import controlls.ReservationManager;
import controlls.CrewManager;
import controlls.Utilities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import models.TheObject;

/**
 *
 * @author BAO TRAN
 */
public class CommonMenuNew {

    public void displayCommonMenu() throws IOException, FileNotFoundException, ClassNotFoundException, Exception {

        int choice;
        FlightManager fm = new FlightManager();
        FlightManager fm1 = new FlightManager();
        FlightManager flights = new FlightManager();
        ReservationManager rm = new ReservationManager();
        CrewManager cm = new CrewManager();

        TheObject o = new TheObject(fm1.getList(), cm.getList(), rm.getList());
        TheObject t = new TheObject();

        boolean quit = false;

        do {
            System.out.println("");
            System.out.println("<-------------------------------------------------------------------------->");
            System.out.println("┃============================= MAIN*MENU ================================┃");
            System.out.println("┃     1. Flight schedule management                                      ┃");
            System.out.println("┃     2. Passenger reservation and booking                               ┃");
            System.out.println("┃     3. Passenger check-in and seat allocation                          ┃");
            System.out.println("┃     4. Crew management and assignments                                 ┃");
            System.out.println("┃     5. Administrator access for system management                      ┃");
            System.out.println("┃     6. Data storage for flight details, reservations, and assignments  ┃");
            System.out.println("┃     7. Save Data to file                                               ┃");
            System.out.println("┃     8. Quit                                                            ┃");
            System.out.println("<-------------------------------------------------------------------------->");

            Scanner sc = new Scanner(System.in);
            choice = Utilities.getInt("Enter your choice: ", 0, 10);

            if (choice == 1) {
                System.out.println("");
                System.out.println("Add a new Flight's information: ");
                fm.addFlight();
                displayCommonMenu();
            }

            if (choice == 2) {
                System.out.println("");

                rm.Reservation();
                displayCommonMenu();
            }
            if (choice == 3) {
                System.out.println("");
                rm.checkinSeat();
                displayCommonMenu();
            }
            if (choice == 4) {
                System.out.println("<---------------Crew management and assignments : ----------------->");
                cm.crewAssign();
                System.out.println("These flights have crew");
                displayCommonMenu();
            }

            if (choice == 5) {

                int choice1 = -3;
                do {
                    Menu menu2 = new Menu("2. Manage Adnimistrator");
                    menu2.addNewOption("      2.1. Manage Flight Schedule ");
                    menu2.addNewOption("      2.2. Manage Crew Assigment");
                    menu2.addNewOption("      2.3. Back to main menu");
                    menu2.printMenu();

                    choice1 = menu2.getChoice();
                    if (choice1 == 1) {
                        fm1.manageFlightSchedule();
                    }
                    if (choice1 == 2) {
                        cm.manageFlightCrew();
                    }

                } while (choice1 != 3);
                displayCommonMenu();
            }

            if (choice == 6) {
                System.out.println("");
                System.out.println("Display all data: ");
                String s1 = "";

                System.out.println("Display Flights by date descending: ");
                System.out.println("----------------------------------------------------------------------------");
                fm.displayDescendingByDate(o);
                System.out.println("----------------------------------------------------------------------------");

                displayCommonMenu();

            }
            if (choice == 7) {

                o = fm1.getObject();
                TheObject.saveObject(o, "src//output//object.dat");
                System.out.println("Successfully!!!");
                displayCommonMenu();
            }
            if (choice == 8) {

                System.out.println("Good bye!!!");
                break;
            }
        } while (choice == 8);
    }

}
