package org.example.Actions;

import org.example.Information.AdminInfo;
import org.example.Information.CoworkingPlace;

// register and login
// add a new coworking place
// remove coworking place
// view all reservations
import java.util.ArrayList;
import java.util.Scanner;

public class AdminMethods {
    private ArrayList<AdminInfo> adminList = new ArrayList<>();
    private ArrayList<CoworkingPlace> coworkingList = new ArrayList<>();

    // Регистрация администратора
    public void registerAdmin(Scanner scanner) {
        System.out.println("Регистрация нового администратора:");
        AdminInfo newAdmin = new AdminInfo(scanner);
        adminList.add(newAdmin);
        System.out.println("Администратор добавлен: " + newAdmin);
    }

    public ArrayList<CoworkingPlace> getCoworkingList() {
        return coworkingList;
    }

    // Показ всех администраторов
    public void showAllAdmins() {
        if (adminList.isEmpty()) {
            System.out.println("Список администраторов пуст. Пройдите регистрацию.");
        } else {
            System.out.println("Список всех администраторов:");
            for (int i = 0; i < adminList.size(); i++) {
                System.out.println((i + 1) + ". " + adminList.get(i));
            }
        }
    }

    // Авторизация администратора
    public void loginAdmin(Scanner scanner) {
        System.out.println("Авторизация администратора:");
        if (adminList.isEmpty()) {
            System.out.println("Список администраторов пуст. Сначала пройдите регистрацию!");
            return;
        }

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите пароль: ");
        int password = scanner.nextInt();
        scanner.nextLine();
        boolean isAuthorized = false;

        for (AdminInfo admin : adminList) {
            if (admin.getName().equals(name) && admin.getPassword() == password) {
                System.out.println("Добро пожаловать, " + name + "!");
                isAuthorized = true;
                break;
            }
        }

        if (!isAuthorized) {
            System.out.println("Ошибка авторизации. Неверное имя или пароль.");
        }
    }

    public void addCoworkingPlace(Scanner scanner2){
        System.out.println("Добавление нового рабочего места");
        CoworkingPlace newCoworkingPlace = new CoworkingPlace(scanner2);
        coworkingList.add(newCoworkingPlace);
        System.out.println("Вы успешно добавили новое место");
    }

    public void removeCoworkingPlace(Scanner scanner) {
        System.out.println("Удаление рабочего места");
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

    public void updateCoworkingPlace(Scanner scanner){

    }
}

