package org.example.Actions;

import org.example.CustomException.CustomException;
import org.example.Information.CoworkingPlace;
import org.example.Information.UserInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserMethods {
    private AdminMethods adminMethods;
    private ArrayList<UserInfo> userList = new ArrayList<>();

    public UserMethods(AdminMethods adminMethods) {
        this.adminMethods = adminMethods;
    }

    public void registerUser(Scanner scanner) throws CustomException.NotAuthorizedUser {
        System.out.println("Registration of a new user");
        UserInfo newUser = new UserInfo(scanner);
        userList.add(newUser);
        System.out.println("You have successfully added a new user");

        // Throw an exception if the user list is empty
        if (userList.isEmpty()) {
            throw new CustomException.NotAuthorizedUser("You have not completed registration");
        }
    }

    public void loginUser(Scanner scanner) throws CustomException.EmptyUserList {
        System.out.println("User login");

        // Notify if the user list is empty
        if (userList.isEmpty()) {
            throw new CustomException.EmptyUserList("The user list is empty. Please complete registration.");
        }

        System.out.println("Enter first name");
        String name = scanner.nextLine();
        System.out.println("Enter last name");
        String surname = scanner.nextLine();
        System.out.println("Enter password");
        int password = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer

        boolean isAuthorized = false;

        // Check the user login
        for (UserInfo user : userList) {
            if (user.getName().equals(name) && user.getSurname().equals(surname) && user.getPassword() == password) {
                System.out.println("We are glad to see you, " + name + " " + surname + "!");
                isAuthorized = true;
                break;
            }
        }

        // If no match is found, throw an exception
        if (!isAuthorized) {
            throw new CustomException.EmptyUserList("User not found, please register.");
        }
    }

    public void viewAllCoworkingPlaces() {
        ArrayList<CoworkingPlace> coworkingList = adminMethods.getCoworkingList();
        if (coworkingList.isEmpty()) {
            System.out.println("No available places.");
            return;
        }

        System.out.println("Available places:");
        for (CoworkingPlace freePlace : coworkingList) {
            if (freePlace.isAvailable()) {
                System.out.println(freePlace);
            }
        }
    }

    public void makeReservations(Scanner scanner) {
        ArrayList<CoworkingPlace> coworkingList = adminMethods.getCoworkingList();
        boolean availablePlaceFound = false;

        for (CoworkingPlace place : coworkingList) {
            if (place.isAvailable()) {
                System.out.println("Reserving place: " + place);
                place.setAvailable(false);  // Mark the place as booked
                availablePlaceFound = true;
                break;
            }
        }

        if (!availablePlaceFound) {
            System.out.println("No available places.");
        }
    }

    public void addUserFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(";");
                if (userData.length == 3) { // length == 3 meaning that our userData accepted all 3 parameters like name surname and password
                    String name = userData[0];
                    String surname = userData[1];
                    int password = Integer.parseInt(userData[2]);
                    UserInfo user = new UserInfo(name, surname, password);
                    userList.add(user);
                    System.out.println("User added from file: " + name + " " + surname);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    public void cancelReservations(Scanner scanner) {
        System.out.println("Enter the coworking place ID to cancel reservation:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (CoworkingPlace place : adminMethods.getCoworkingList()) {
            if (place.getId() == id && !place.isAvailable()) {
                place.setAvailable(true);
                System.out.println("Reservation cancelled for place with ID " + id);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Place with this ID not found or it was not reserved.");
        }
    }
}
