package src.ui;
import java.util.HashMap;
import java.util.Scanner;
import src.models.Company;
import src.services.ApplicationService;

public class ConsoleUI {
    private final HashMap<String, String> map = new HashMap<>(4);
    private ApplicationService service;
    private Scanner scanner;
    public boolean isClosed;
    
    public ConsoleUI(ApplicationService service, Scanner scanner){
        this.service = service;
        this.scanner = scanner;
        this.isClosed = false;

        map.put("Cname","Enter name of the company: ");
        map.put("description", "Enter description: ");
        map.put("url", "Enter url: ");
        map.put("n","Enter number of employees: ");
    }
    
    public void setService(ApplicationService service) {
        this.service = service;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu(){
        System.out.println("""
                           Menu:
                           1: Add company
                           2: Add application
                           3: List all companies
                           4: List all applications
                           5: Find company by id
                           6: Find application by id
                           7: List applications by company id
                           8: List applications by status
                           9: Update application status
                           10: Check application followup date
                           11: Delete company
                           12: Delete application
                           13: Exit.
                           """);
    }
    private String getInput(String argument){
        System.out.print(argument);
        String input = this.scanner.next();
        return input;
    }

    private Company addCompanyHandler() {
        
        try {
            return this.service.addCompany(
                getInput(map.get("Cname")),
                getInput(map.get("description")),
                getInput(map.get("url")),
                Integer.parseInt(getInput(map.get("n"))));
           
        } catch (NumberFormatException e) {
            System.out.println("Caught Exception: " + e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }


    public void handleSelectedOption( int selectedOption ){
        
       
        switch (selectedOption){
            case 1 -> addCompanyHandler();
            case 3 -> System.out.println(this.service.getCompanies());
            case 13 -> exit();
        }
        
        

    }

    public void exit(){
        try {
            scanner.close();
            System.out.println("Exiting Application ...");
            this.isClosed = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    private void deleteCompanyHandler(){
        System.out.print("Enter company id: ");
        service.deleteCompanyById(this.scanner.nextInt());
    }

}
