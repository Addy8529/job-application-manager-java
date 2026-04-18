package src.ui;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.URI;
import java.net.URISyntaxException;

import src.services.ApplicationService;

public class ConsoleUI {
    
    private ApplicationService service;
    private Scanner scanner;
    
    public ConsoleUI(ApplicationService service, Scanner scanner){
        this.service = service;
        this.scanner = scanner;
    }
    
    public void setService(ApplicationService service) {
        this.service = service;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu(){
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
            "12: Delete application\n" +
            "13: Exit.\n"
        );
    }
    private void addCompanyHandler() {
        String[] prompts = {
            "Enter company name: ",
            "Enter description: ",
            "Enter url: ",
            "Enter number of employees: "
        };
        ArrayList<String> arguments = new ArrayList<>();

        for ( String promt : prompts ) {
            System.out.print(promt);
            arguments.add(scanner.next());
        }
        
        service.addCompany(arguments.get(0), arguments.get(1), arguments.get(2), Integer.parseInt(arguments.get(3)));
    }

    public void exit(){
        scanner.close();
    }
    public void handleSelectedOption( int selectedOption ){
        
        switch (selectedOption){
            case 1 -> addCompanyHandler();
            case 13 -> exit();
        }

    }

    

}
