/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import models.Flight;
import models.Reservation;
import models.Schedule;

/**
 *
 * @author BAO TRAN
 */
public class Utilities {

    private static Scanner sc = new Scanner(System.in);

    public static int getInt(String sms, int min, int max) {
        int n = 0;

        while (true) {
            try {
                System.out.println(sms);
                n = Integer.parseInt(sc.nextLine());
                if (n >= min && n < max) {
                    return n;
                }

            } catch (NumberFormatException e) {
                System.out.println();
            }
        }
    }

    public static double getDouble(String prompt, double min, double max) {
        double number = 0;
        boolean isValid = false;

        do {
            try {
                System.out.println(prompt);
                number = Double.parseDouble(sc.nextLine());

                if (number > min && number < max) {
                    isValid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!isValid);

        return number;
    }

    public static String getString(String sms, int opt, int min, int max) {
        String s = "";
        while (true) {
            try {
                System.out.println(sms);
                s = sc.nextLine();

                if (opt == 1) {
                    if (!s.isEmpty() && s.length() > min && s.length() < max) {
                        return s;
                    }
                }
                if (opt == 2) {

                    if (s.isEmpty()) {
                        System.out.println("Please enter a string of at least 1 character!!!");
                    }
                }
            } catch (Exception e) {
                System.out.println("PLease enter the valid information!");
            }

        }
    }

    public static String inputDate(String msg, int opt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date currentDate = new Date();
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println(msg);
                String input = sc.nextLine().trim();

                if (input.isEmpty() || input.equals("")) {
                    return null;
                }

                Date date = dateFormat.parse(input);

                if (opt == 1) {
                    if (date.after(currentDate)) {
                        System.out.println("Please input a date before the current date!");
                        continue;
                    }
                }

                String formattedDate = dateFormat.format(date);
                return formattedDate;
            } catch (ParseException e) {
                System.out.println("Please input a valid date and time (yyyy/MM/dd HH:mm).");
            }
        } while (true);
    }

    public static String inputDate1(String msg, int option) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        LocalDate check = null;
        String input = "";

        do {
            try {
                System.out.println(msg);
                input = sc.nextLine().trim();
                if (input.isEmpty() || input.equals("")) {
                    return null;
                }
                check = LocalDate.parse(input, formatter);
                if (option == 1) {
                    if (check.isAfter(now)) {
                        System.out.println("Please input a date before the current date!");
                        continue;
                    }
                }
                return check.format(formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Please input a valid date (yyyy/MM/dd)!");
            }
        } while (true);
    }

    public static ArrayList<Object> loadData(String filename) {
        ArrayList<Object> loadedData = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            loadedData.add(objectIn.readObject());
            loadedData.add(objectIn.readObject());
            loadedData.add(objectIn.readObject());

            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedData;
    }

    public static void saveData(String filename, ArrayList<Flight> flightDetailsList,
            ArrayList<Reservation> reservationsList,
            ArrayList<Schedule> assignmentsList) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(flightDetailsList);
            objectOut.writeObject(reservationsList);
            objectOut.writeObject(assignmentsList);

            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String filename, ArrayList<Flight> flightDetailsList,
            ArrayList<Reservation> reservationsList,
            ArrayList<Schedule> assignmentsList) {
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            flightDetailsList.addAll((ArrayList<Flight>) objectIn.readObject());
            reservationsList.addAll((ArrayList<Reservation>) objectIn.readObject());
            assignmentsList.addAll((ArrayList<Schedule>) objectIn.readObject());

            objectIn.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAllData() {

    }
}
