package org.example.Information;

import java.util.Scanner;

public class CoworkingPlace {
    private int id;
    private String name;
    private boolean available;

    // Constructor for creating an object with parameters
    public CoworkingPlace(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    // Constructor for input via Scanner
    public CoworkingPlace(Scanner scanner) {
        System.out.print("Enter coworking space ID: ");
        this.id = scanner.nextInt();
        scanner.nextLine();  // Clear the buffer
        System.out.print("Enter coworking space name: ");
        this.name = scanner.nextLine();
        System.out.print("Is the coworking space available (true/false): ");
        this.available = scanner.nextBoolean();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Available: " + available;
    }
}
