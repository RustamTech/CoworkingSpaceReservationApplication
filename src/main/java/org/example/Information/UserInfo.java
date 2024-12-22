package org.example.Information;
import java.util.*;

public class UserInfo {
    private String name;
    private String surname;
    private String number;
    private int password;
    private double amount;

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UserInfo(Scanner scanner){
        System.out.println("Введите имя: ");
        this.name = scanner.nextLine();

        System.out.println("Введите фамилию: ");
        this.surname = scanner.nextLine();

        System.out.println("Введите номер телефона: ");
        this.number = scanner.nextLine();

        System.out.println("Введите пароль: ");
        this.password = scanner.nextInt();

        System.out.println("Введите баланс" );
        this.amount = scanner.nextDouble();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", number='" + number + '\'' +
                ", password=" + password +
                ", amount=" + amount +
                '}';
    }
}
