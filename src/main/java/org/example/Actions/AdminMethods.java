package org.example.Actions;

import org.example.CustomException.CustomException;
import org.example.Information.CoworkingPlace;
import org.example.Information.AdminInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMethods {
    private ArrayList<AdminInfo> adminList = new ArrayList<>();
    private ArrayList<CoworkingPlace> coworkingList = new ArrayList<>();

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
        boolean isAuthorized = false;

        for (AdminInfo admin : adminList) {
            if (admin.getName().equals(name) && admin.getPassword() == password) {
                System.out.println("Добро пожаловать, " + name + "!");
                isAuthorized = true;
                break;
            }
        }

        if (!isAuthorized) {
            throw new CustomException.NotAuthorizedUser("Ошибка авторизации.");
        }
    }

    public void addCoworkingPlace(Scanner scanner) {
        System.out.println("Добавление нового рабочего места:");
        CoworkingPlace newCoworkingPlace = new CoworkingPlace(scanner);
        coworkingList.add(newCoworkingPlace);
        System.out.println("Вы успешно добавили новое рабочее место.");
    }

    public void showAllCoworkingPlaces() {
        System.out.println("Все рабочие места:");
        if (coworkingList.isEmpty()) {
            System.out.println("Нет доступных рабочих мест.");
        } else {
            for (CoworkingPlace place : coworkingList) {
                System.out.println(place);  // Автоматически вызовется метод toString() объекта CoworkingPlace
            }
        }
    }


    public void removeCoworkingPlace(Scanner scanner) {
        System.out.println("Удаление рабочего места:");
        if (coworkingList.isEmpty()) {
            System.out.println("Список рабочих мест пуст.");
            return;
        }

        System.out.print("Введите ID рабочего места для удаления: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Очистка буфера
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

    public void addCoworkingPlaceFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coworkingData = line.split(";");
                if (coworkingData.length == 3) {
                    int id = Integer.parseInt(coworkingData[0]);
                    String name = coworkingData[1];
                    boolean available = Boolean.parseBoolean(coworkingData[2]);
                    CoworkingPlace place = new CoworkingPlace(id, name, available);
                    coworkingList.add(place);
                    System.out.println("Рабочее место добавлено: " + place);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
