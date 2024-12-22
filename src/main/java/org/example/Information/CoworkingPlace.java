package org.example.Information;
import java.util.*;

public class CoworkingPlace {
    private int id;
    private String name;
    private boolean isAvailable;
    private double price;
    private int roomCount;

    public CoworkingPlace(Scanner scanner) {
        System.out.print("Введите ID: ");
        this.id = scanner.nextInt();

        scanner.nextLine(); // Считывание символа новой строки после nextInt()

        System.out.print("Введите название: ");
        this.name = scanner.nextLine();

        System.out.print("Свободное место? (true/false): ");
        this.isAvailable = scanner.nextBoolean();

        System.out.print("Введите цену: ");
        this.price = scanner.nextDouble();

        System.out.print("Введите количество комнат: ");
        this.roomCount = scanner.nextInt();
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public String toString() {
        return "CoworkingPlace{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", roomCount=" + roomCount +
                '}';
    }
}