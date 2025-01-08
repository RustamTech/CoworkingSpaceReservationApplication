package org.example.Information;

import java.util.Scanner;

public class CoworkingPlace {
    private int id;
    private String name;
    private boolean available;

    // Конструктор для создания объекта с параметрами
    public CoworkingPlace(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    // Конструктор для ввода через Scanner
    public CoworkingPlace(Scanner scanner) {
        System.out.print("Введите ID рабочего места: ");
        this.id = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера
        System.out.print("Введите название рабочего места: ");
        this.name = scanner.nextLine();
        System.out.print("Доступность рабочего места (true/false): ");
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
        return "ID: " + id + ", Название: " + name + ", Доступно: " + available;
    }
}
