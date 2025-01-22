package org.example.Information;
import java.util.Scanner;

public class AdminInfo {
    private String name;
    private String surname;
    private String number;
    private int password;

    // Конструктор, принимающий Scanner для ввода данных
    public AdminInfo(Scanner scanner) {
        System.out.print("Print your name please:");
        this.name = scanner.nextLine();

        System.out.print("Print your surname please");
        this.surname = scanner.nextLine();

        System.out.print("Print your number please:");
        this.number = scanner.nextLine();

        System.out.print("Print your password please:");
        this.password = scanner.nextInt();
        scanner.nextLine();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AdminInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                ", password=" + password +
                '}';
    }
}