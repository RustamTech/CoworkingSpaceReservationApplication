package org.example.Actions;

import org.example.CustomException.CustomException;
import org.example.Information.CoworkingPlace;
import org.example.Information.AdminInfo;
import org.example.Information.Report;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMethods {
    private ArrayList<AdminInfo> adminList = new ArrayList<>();
    private ArrayList<CoworkingPlace> coworkingList = new ArrayList<>();
    private boolean isAuthorized = false;  // point which means that our admin has passed registration and login

    public void registerAdmin(Scanner scanner) {
        System.out.println("Registering a new administrator:");
        AdminInfo newAdmin = new AdminInfo(scanner);
        adminList.add(newAdmin);
        System.out.println("Administrator added: " + newAdmin);
    }

    public ArrayList<CoworkingPlace> getCoworkingList() {
        return coworkingList;
    }

    public void showAllAdmins() throws CustomException.EmptyAdminList {
        if (adminList.isEmpty()) {
            System.out.println("The administrator list is empty. Please register first.");
            throw new CustomException.EmptyAdminList("The administrator list is empty.");
        }

        System.out.println("List of all administrators:");
        for (int i = 0; i < adminList.size(); i++) {
            System.out.println((i + 1) + ". " + adminList.get(i));
        }
    }

    public void loginAdmin(Scanner scanner) throws CustomException.NotAuthorizedUser {
        System.out.println("Administrator login:");

        if (adminList.isEmpty()) {
            System.out.println("The administrator list is empty. Please register first.");
            return;
        }

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter password: ");
        int password = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer
        boolean isAuthenticated = false;

        for (AdminInfo admin : adminList) {
            if (admin.getName().equals(name) && admin.getPassword() == password) {
                System.out.println("Welcome, " + name + "!");
                isAuthorized = true;  // in this case we change our point
                isAuthenticated = true;
                break;
            }
        }

        if (!isAuthenticated) {
            throw new CustomException.NotAuthorizedUser("Authorization error.");
        }
    }

    public void addCoworkingPlace(Scanner scanner) {
        if (!isAuthorized) {  // Authorization check
            System.out.println("Error: You must be authorized to perform this operation.");
            return;
        }

        System.out.println("Adding a new coworking space:");
        CoworkingPlace newCoworkingPlace = new CoworkingPlace(scanner);
        coworkingList.add(newCoworkingPlace);
        System.out.println("You have successfully added a new coworking space.");
    }

    public void showAllCoworkingPlaces() {
        if (!isAuthorized) {
            System.out.println("Error: You must be authorized to perform this operation.");
            return;
        }

        System.out.println("All coworking spaces:");
        if (coworkingList.isEmpty()) {
            System.out.println("No available coworking spaces.");
        } else {
            for (CoworkingPlace place : coworkingList) {
                System.out.println(place);  // A
            }
        }
    }

    public void removeCoworkingPlace(Scanner scanner) {
        if (!isAuthorized) {  // Authorization check
            System.out.println("Error: You must be authorized to perform this operation.");
            return;
        }

        System.out.println("Removing a coworking space:");
        if (coworkingList.isEmpty()) {
            System.out.println("The coworking spaces list is empty.");
            return;
        }

        System.out.print("Enter the ID of the coworking space to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < coworkingList.size(); i++) {
            CoworkingPlace place = coworkingList.get(i);
            if (place.getId() == id) {
                coworkingList.remove(i);
                System.out.println("Coworking space with ID " + id + " has been successfully deleted.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Coworking space with ID " + id + " not found.");
        }
    }

    public void showFreeSpaces(){
        if (!isAuthorized) {
            System.out.println("Error: You must be authorized to perform this operation.");
            return;
        }

        System.out.println("Information about free spaces:");
        int freeSpacesCount = Report.countFreeSpaces(coworkingList);
        System.out.println("Free spaces: " + freeSpacesCount);
    }

    public void showBookedSpaces(){
        if (!isAuthorized) {
            System.out.println("Error: You must be authorized to perform this operation.");
            return;
        }

        System.out.println("Information about booked spaces:");
        int bookedSpacesCount = Report.countBookedSpaces(coworkingList);
        System.out.println("Booked spaces: " + bookedSpacesCount);
    }
}
