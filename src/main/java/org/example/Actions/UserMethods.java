package org.example.Actions;

import org.example.Information.CoworkingPlace;
import org.example.Information.UserInfo;
import org.example.Actions.AdminMethods;
import java.util.Scanner;

import java.util.ArrayList;

// login
// browse available places
// make and cancel reservations
// views my reservations
public class UserMethods {
    private AdminMethods adminMethods;
    private ArrayList<UserInfo> userList = new ArrayList<>();

    public UserMethods(AdminMethods adminMethods){
        this.adminMethods = adminMethods;
    }


    public void registerUser(Scanner scanner){
        System.out.println("Регистрация нового пользователя");
        UserInfo newUser = new UserInfo(scanner);
        userList.add(newUser);
        System.out.println("Вы успешно добавили нового пользователя");
    }

    public void loginUser(Scanner scanner){
        System.out.println("Авторизация пользователя");
        if(userList.isEmpty()){
            System.out.println("Вы не прошли регистрацию");
            return;
        } else {
            System.out.println("Введите имя");
            String name = scanner.nextLine();
            System.out.println("Введите фамилию");
            String surname = scanner.nextLine();
            System.out.println("Введите пароль");
            int password = scanner.nextInt();
            boolean isAuthorized = false;

            for(UserInfo user : userList){
                if(user.getName().equals(name) && user.getSurname().equals(surname) && user.getPassword() == password){
                    System.out.println("Рады вас видеть " + name + surname +  "!");
                    isAuthorized = true;
                    break;
                }
            }
            if(!isAuthorized){
                System.out.println("Ошибка авторизации, неверное имя или пароль");
            }
        }
    }

    public void viewAllCoworkingPlaces(){
        ArrayList<CoworkingPlace> coworkingList = adminMethods.getCoworkingList();
        if(coworkingList.isEmpty()){
            System.out.println("Нет мест");
            return;
        } else {
        System.out.println("Доступные места: ");
        for(CoworkingPlace freePlace : coworkingList){
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
                place.setAvailable(false);  // Mark place as reserved
                availablePlaceFound = true;
                break;
            }
        }

        if (!availablePlaceFound) {
            System.out.println("Нет доступных мест.");
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
