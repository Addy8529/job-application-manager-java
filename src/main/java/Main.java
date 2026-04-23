
import java.util.Scanner;

import services.ApplicationService;
import ui.ConsoleUI;

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
        System.out.println("Hello World");

        

      
        
        //System.out.println("Env: " + System.getenv().toString());
        
        
    
    }
    
}