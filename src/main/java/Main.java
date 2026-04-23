package src.main.java;

import java.util.Scanner;

import src.main.java.services.ApplicationService;
import src.main.java.ui.ConsoleUI;

public class Main{

    public static void start(){
        Scanner scanner =  new Scanner(System.in);
        scanner.useDelimiter("\n");
        
        ApplicationService service =  new ApplicationService();
        ConsoleUI ui = new ConsoleUI(service, scanner);
        
        while ( !ui.isClosed ){
            
            try {
                ui.printMenu();
                ui.handleSelectedOption(scanner.nextInt());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
    }

    public static void main(String[] args) {
        //start();
        //System.err.println("This was an error");

        

      
        
        //System.out.println("Env: " + System.getenv().toString());
        
        
    
    }
    
}