package org.example.Information;
import java.util.List;
import org.example.Information.CoworkingPlace;

public class Report {
    //1.Creating specific method which be return count of free spaces
    public static int countFreeSpaces(List<CoworkingPlace> coworkingList){
        int freeSpacesCount = 0;
        for(CoworkingPlace space : coworkingList){
            if(space.isAvailable()){
                freeSpacesCount++;
            }
        }
        return freeSpacesCount;
    }
    //2.Creating specific method which be return count of booked spaces
    public static int countBookedSpaces(List<CoworkingPlace> coworkingList) {
        int bookedSpacesCount = 0;
        for (CoworkingPlace space : coworkingList) {
            if (!space.isAvailable()) {
                bookedSpacesCount++;
            }
        }
        return bookedSpacesCount;
    }

    public static void generateInformationAboutReport(List<CoworkingPlace> coworkingList){
        int freeSpacesCount = countFreeSpaces(coworkingList);
        int bookedSpacesCount = countBookedSpaces(coworkingList);
        System.out.println("Information about report :");
        System.out.println("Free places " + freeSpacesCount);
        System.out.println("Booked places" + bookedSpacesCount);
    }
}
