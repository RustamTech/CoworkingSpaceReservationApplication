package org.example.Information;

import java.util.Scanner;

public class UserInfo {
    private String name;
    private String surname;
    private int password;

    // Конструктор для создания объекта с параметрами
    public UserInfo(String name, String surname, int password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    // Конструктор для ввода через Scanner
    public UserInfo(Scanner scanner) {
        System.out.print("Введите имя: ");
        this.name = scanner.nextLine();
        System.out.print("Введите фамилию: ");
        this.surname = scanner.nextLine();
        System.out.print("Введите пароль: ");
        this.password = scanner.nextInt();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Фамилия: " + surname + ", Пароль: " + password;
    }
}

