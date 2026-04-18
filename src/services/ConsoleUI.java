package src.services;

public class ConsoleUI {
    
    public static void printMenu(){
        System.out.println("Menu:\n" + 
            "1: Add company\n" + 
            "2: Add application\n" +
            "3: List all companies\n" + 
            "4: List all applications\n" +
            "5: Find company by id\n" +
            "6: Find application by id\n" +
            "7: List applications by company id\n" +
            "8: List applications by status\n" +
            "9: Update application status\n" +
            "10: Check application followup date\n" +
            "11: Delete company\n" +
            "12: Delete application\n" +"\u001B[0m"
        );
    }
    
}
