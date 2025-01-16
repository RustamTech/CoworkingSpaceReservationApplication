package org.example.Console;

import org.example.Actions.AdminMethods;
import org.example.Actions.UserMethods;
import org.example.Information.CoworkingPlace;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating user and admins methods
        AdminMethods adminMethods = new AdminMethods();
        UserMethods userMethods = new UserMethods(adminMethods);

        // Greetings
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

    // This is menu of admin
    public static void adminMenu(Scanner scanner, AdminMethods adminMethods) {
        while (true) {
            System.out.println("\nМеню администратора:");
            System.out.println("1. Регистрация администратора");
            System.out.println("2. Вход администратора");
            System.out.println("3. Добавить новое рабочее место");
            System.out.println("4. Удалить рабочее место");
            System.out.println("5. Посмотреть все рабочие места");
            System.out.println("6. Посмотреть все свободные места");
            System.out.println("7. Посмотреть все занятые места");
            System.out.println("8. Выход в главное меню");
            System.out.print("Ваш выбор: ");
            int adminChoice = scanner.nextInt();
            scanner.nextLine();

            switch (adminChoice) {
                case 1:
                    try {
                        adminMethods.registerAdmin(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при регистрации администратора: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        adminMethods.loginAdmin(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при входе администратора: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        adminMethods.addCoworkingPlace(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при добавлении рабочего места: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        adminMethods.removeCoworkingPlace(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при удалении рабочего места: " + e.getMessage());
                    }
                    break;
                case 5:
                    adminMethods.showAllCoworkingPlaces();
                    break;
                case 6:
                    adminMethods.showFreeSpaces();
                    break;
                case 7:
                    adminMethods.showBookedSpaces();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // This is menu of user
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
                case 1:
                    try {
                        userMethods.registerUser(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при регистрации пользователя: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        userMethods.loginUser(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при входе пользователя: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        userMethods.makeReservations(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при резервировании рабочего места: " + e.getMessage());
                    }
                    break;
                case 4:
                    userMethods.viewAllCoworkingPlaces();
                    break;
                case 5:
                    try {
                        userMethods.cancelReservations(scanner);
                    } catch (Exception e) {
                        System.out.println("Ошибка при отмене резервирования: " + e.getMessage());
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
