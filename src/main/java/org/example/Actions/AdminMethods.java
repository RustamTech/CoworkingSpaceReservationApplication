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
    private boolean isAuthorized = false;  // point which mean that out admin has passed registration and login

    public void registerAdmin(Scanner scanner) {
        System.out.println("Регистрация нового администратора:");
        AdminInfo newAdmin = new AdminInfo(scanner);
        adminList.add(newAdmin);
        System.out.println("Администратор добавлен: " + newAdmin);
    }

    public ArrayList<CoworkingPlace> getCoworkingList() {
        return coworkingList;
    }

    public void showAllAdmins() throws CustomException.EmptyAdminList {
        if (adminList.isEmpty()) {
            System.out.println("Список администраторов пуст. Пройдите регистрацию.");
            throw new CustomException.EmptyAdminList("Список администраторов пуст.");
        }

        System.out.println("Список всех администраторов:");
        for (int i = 0; i < adminList.size(); i++) {
            System.out.println((i + 1) + ". " + adminList.get(i));
        }
    }

    public void loginAdmin(Scanner scanner) throws CustomException.NotAuthorizedUser {
        System.out.println("Авторизация администратора:");

        if (adminList.isEmpty()) {
            System.out.println("Список администраторов пуст. Сначала пройдите регистрацию.");
            return;
        }

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите пароль: ");
        int password = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера
        boolean isAuthenticated = false;

        for (AdminInfo admin : adminList) {
            if (admin.getName().equals(name) && admin.getPassword() == password) {
                System.out.println("Добро пожаловать, " + name + "!");
                isAuthorized = true;  // in this case we change out point
                isAuthenticated = true;
                break;
            }
        }

        if (!isAuthenticated) {
            throw new CustomException.NotAuthorizedUser("Ошибка авторизации.");
        }
    }

    public void addCoworkingPlace(Scanner scanner) {
        if (!isAuthorized) {  // Проверка на авторизацию
            System.out.println("Ошибка: для выполнения этой операции нужно пройти авторизацию.");
            return;
        }

        System.out.println("Добавление нового рабочего места:");
        CoworkingPlace newCoworkingPlace = new CoworkingPlace(scanner);
        coworkingList.add(newCoworkingPlace);
        System.out.println("Вы успешно добавили новое рабочее место.");
    }

    public void showAllCoworkingPlaces() {
        if (!isAuthorized) {
            System.out.println("Ошибка: для выполнения этой операции нужно пройти авторизацию.");
            return;
        }

        System.out.println("Все рабочие места:");
        if (coworkingList.isEmpty()) {
            System.out.println("Нет доступных рабочих мест.");
        } else {
            for (CoworkingPlace place : coworkingList) {
                System.out.println(place);  // А
            }
        }
    }

    public void removeCoworkingPlace(Scanner scanner) {
        if (!isAuthorized) {  // Проверка на авторизацию
            System.out.println("Ошибка: для выполнения этой операции нужно пройти авторизацию.");
            return;
        }

        System.out.println("Удаление рабочего места:");
        if (coworkingList.isEmpty()) {
            System.out.println("Список рабочих мест пуст.");
            return;
        }

        System.out.print("Введите ID рабочего места для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < coworkingList.size(); i++) {
            CoworkingPlace place = coworkingList.get(i);
            if (place.getId() == id) {
                coworkingList.remove(i);
                System.out.println("Рабочее место с ID " + id + " успешно удалено.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Рабочее место с ID " + id + " не найдено.");
        }
    }

    public void showFreeSpaces(){
        if (!isAuthorized) {
            System.out.println("Ошибка: для выполнения этой операции нужно пройти авторизацию.");
            return;
        }

        System.out.println("Информация о свободных местах:");
        int freeSpacesCount = Report.countFreeSpaces(coworkingList);
        System.out.println("Свободные места: " + freeSpacesCount);
    }

    public void showBookedSpaces(){
        if (!isAuthorized) {
            System.out.println("Ошибка: для выполнения этой операции нужно пройти авторизацию.");
            return;
        }

        System.out.println("Информация о занятых местах:");
        int bookedSpacesCount = Report.countBookedSpaces(coworkingList);
        System.out.println("Занятые места: " + bookedSpacesCount);
    }
}
