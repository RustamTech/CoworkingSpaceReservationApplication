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
        System.out.println("Регистрация нового пользователя");
        UserInfo newUser = new UserInfo(scanner);
        userList.add(newUser);
        System.out.println("Вы успешно добавили нового пользователя");

        // Выбрасываем исключение, если список пользователей пуст
        if (userList.isEmpty()) {
            throw new CustomException.NotAuthorizedUser("Вы не прошли регистрацию");
        }
    }

    public void loginUser(Scanner scanner) throws CustomException.EmptyUserList {
        System.out.println("Авторизация пользователя");

        // Если список пользователей пуст, уведомляем об этом
        if (userList.isEmpty()) {
            throw new CustomException.EmptyUserList("Список пользователей пуст. Пройдите регистрацию.");
        }

        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        System.out.println("Введите пароль");
        int password = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера

        boolean isAuthorized = false;

        // Проверяем авторизацию пользователя
        for (UserInfo user : userList) {
            if (user.getName().equals(name) && user.getSurname().equals(surname) && user.getPassword() == password) {
                System.out.println("Рады вас видеть " + name + " " + surname + "!");
                isAuthorized = true;
                break;
            }
        }

        // Если не найдено совпадений, выбрасываем исключение
        if (!isAuthorized) {
            throw new CustomException.EmptyUserList("Пользователь не найден, пройдите регистрацию.");
        }
    }

    public void viewAllCoworkingPlaces() {
        ArrayList<CoworkingPlace> coworkingList = adminMethods.getCoworkingList();
        if (coworkingList.isEmpty()) {
            System.out.println("Нет доступных мест.");
            return;
        }

        System.out.println("Доступные места:");
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
                System.out.println("Резервируем место: " + place);
                place.setAvailable(false);  // Помечаем место как занятое
                availablePlaceFound = true;
                break;
            }
        }

        if (!availablePlaceFound) {
            System.out.println("Нет доступных мест.");
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
                    System.out.println("Пользователь добавлен из файла: " + name + " " + surname);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void cancelReservations(Scanner scanner) {
        System.out.println("Введите ID рабочего места для отмены бронирования:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (CoworkingPlace place : adminMethods.getCoworkingList()) {
            if (place.getId() == id && !place.isAvailable()) {
                place.setAvailable(true);
                System.out.println("Резервирование отменено для места с ID " + id);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Место с таким ID не найдено или оно не было зарезервировано.");
        }
    }
}

