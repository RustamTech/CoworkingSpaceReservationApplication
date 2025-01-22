package org.example.Console;

import org.example.Actions.AdminMethods;
import org.example.Actions.UserMethods;
import org.example.Information.CoworkingPlace;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating user and admin methods
        AdminMethods adminMethods = new AdminMethods();
        UserMethods userMethods = new UserMethods(adminMethods);

        // Greetings
        System.out.println("Welcome to the coworking space booking system!");

        while (true) {
            System.out.println("\nSelect mode:");
            System.out.println("1. Admin menu");
            System.out.println("2. User menu");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            int mainMenuChoice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (mainMenuChoice) {
                case 1:
                    adminMenu(scanner, adminMethods);
                    break;
                case 2:
                    userMenu(scanner, userMethods);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // This is the admin menu
    public static void adminMenu(Scanner scanner, AdminMethods adminMethods) {
        while (true) {
            System.out.println("\nAdmin menu:");
            System.out.println("1. Admin registration");
            System.out.println("2. Admin login");
            System.out.println("3. Add new coworking space");
            System.out.println("4. Remove coworking space");
            System.out.println("5. View all coworking spaces");
            System.out.println("6. View all free spaces");
            System.out.println("7. View all occupied spaces");
            System.out.println("8. Return to the main menu");
            System.out.print("Your choice: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    try {
                        adminMethods.registerAdmin(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during admin registration: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        adminMethods.loginAdmin(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during admin login: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        adminMethods.addCoworkingPlace(scanner);
                    } catch (Exception e) {
                        System.out.println("Error adding coworking space: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        adminMethods.removeCoworkingPlace(scanner);
                    } catch (Exception e) {
                        System.out.println("Error removing coworking space: " + e.getMessage());
                    }
                    break;
                case 5:
                    adminMethods.showAllCoworkingPlaces();
                    break;
                case 6:
                    adminMethods.showFreeSpaces();
                    break;
                case 7:
                    adminMethods.showBookedSpaces();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // This is the user menu
    public static void userMenu(Scanner scanner, UserMethods userMethods) {
        while (true) {
            System.out.println("\nUser menu:");
            System.out.println("1. User registration");
            System.out.println("2. User login");
            System.out.println("3. Make a reservation");
            System.out.println("4. View available spaces");
            System.out.println("5. Cancel reservation");
            System.out.println("6. Return to the main menu");
            System.out.print("Your choice: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (userChoice) {
                case 1:
                    try {
                        userMethods.registerUser(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during user registration: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        userMethods.loginUser(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during user login: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        userMethods.makeReservations(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during coworking space reservation: " + e.getMessage());
                    }
                    break;
                case 4:
                    userMethods.viewAllCoworkingPlaces();
                    break;
                case 5:
                    try {
                        userMethods.cancelReservations(scanner);
                    } catch (Exception e) {
                        System.out.println("Error during reservation cancellation: " + e.getMessage());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
