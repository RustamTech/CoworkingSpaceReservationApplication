package org.example.Console;
import org.example.Actions.AdminMethods;
import org.example.Actions.UserMethods;
import org.example.Information.AdminInfo;
import org.example.Information.CoworkingPlace;
import org.example.Information.UserInfo;

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаем объекты для администраторских и пользовательских методов
        AdminMethods adminMethods = new AdminMethods();
        UserMethods userMethods = new UserMethods(adminMethods);

        // Приветствие
        System.out.println("Добро пожаловать в систему бронирования рабочего места!");

        while (true) {
            System.out.println("\nВыберите режим:");
            System.out.println("1. Меню администратора");
            System.out.println("2. Меню пользователя");
            System.out.println("3. Выход");
            System.out.print("Ваш выбор: ");
            int mainMenuChoice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (mainMenuChoice) {
                case 1:
                    adminMenu(scanner, adminMethods);
                    break;
                case 2:
                    userMenu(scanner, userMethods);
                    break;
                case 3:
                    System.out.println("Выход из программы. До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Меню администратора
    public static void adminMenu(Scanner scanner, AdminMethods adminMethods) {
        while (true) {
            System.out.println("\nМеню администратора:");
            System.out.println("1. Регистрация администратора");
            System.out.println("2. Вход администратора");
            System.out.println("3. Добавить новое рабочее место");
            System.out.println("4. Удалить рабочее место");
            System.out.println("5. Посмотреть все рабочие места");
            System.out.println("6. Выход в главное меню");
            System.out.print("Ваш выбор: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (adminChoice) {
                case 1: // Регистрация администратора
                    adminMethods.registerAdmin(scanner);
                    break;
                case 2: // Вход администратора
                    adminMethods.loginAdmin(scanner);
                    break;
                case 3: // Добавить рабочее место
                    adminMethods.addCoworkingPlace(scanner);
                    break;
                case 4: // Удалить рабочее место
                    adminMethods.removeCoworkingPlace(scanner);
                    break;
                case 5: // Посмотреть все рабочие места
                    System.out.println("\nВсе рабочие места:");
                    for (CoworkingPlace place : adminMethods.getCoworkingList()) {
                        System.out.println(place);
                    }
                    break;
                case 6: // Выход в главное меню
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Меню пользователя
    public static void userMenu(Scanner scanner, UserMethods userMethods) {
        while (true) {
            System.out.println("\nМеню пользователя:");
            System.out.println("1. Регистрация пользователя");
            System.out.println("2. Вход пользователя");
            System.out.println("3. Сделать резервирование");
            System.out.println("4. Посмотреть доступные места");
            System.out.println("5. Отменить резервирование");
            System.out.println("6. Выход в главное меню");
            System.out.print("Ваш выбор: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (userChoice) {
                case 1: // Регистрация пользователя
                    userMethods.registerUser(scanner);
                    break;
                case 2: // Вход пользователя
                    userMethods.loginUser(scanner);
                    break;
                case 3: // Сделать резервирование
                    userMethods.makeReservations(scanner);
                    break;
                case 4: // Посмотреть доступные места
                    userMethods.viewAllCoworkingPlaces();
                    break;
                case 5: // Отменить резервирование
                    userMethods.cancelReservations(scanner);
                    break;
                case 6: // Выход в главное меню
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
     }
}
