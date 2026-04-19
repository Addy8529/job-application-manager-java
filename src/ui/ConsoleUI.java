package src.ui;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import src.models.ApplicationStatus;
import src.models.Company;
import src.models.JobType;
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
        map.put("CompanyId","Enter company id: ");
        map.put("roleTitle", "Enter job title: ");
        map.put("jobType", """
                                Enter working hours:
                                120: Full time
                                80: Part time
                                100: Internship
                                40: Working Student             
                                """
            );
        map.put("status: ", """
                                Enter application status: 
                                0: APPLIED
                                1: INTERVIEW
                                2: OFFER
                                3: REJECTED
                                4: ACCEPTED
                                5: WITHDRAWN    
                            """ );
        map.put("dateApplied", "Enter application date like YYYY-MM-DD: " );
        map.put("followUpDate", "Enter followup date like YYYY-MM-DD: " );
        map.put("notes", "Enter notes: ");

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
        System.out.print(map.get(argument));
        String input = this.scanner.next();
        return input;
    }

    private int getInputInt(String argument){
        System.out.print(map.get(argument));
        return this.scanner.nextInt();
    }

    private Company addCompanyHandler() {
        
        try {
            return this.service.addCompany(
                getInput("Cname"),
                getInput("description"),
                getInput("url"),
                getInputInt("n"));
           
        } catch (NumberFormatException e) {
            System.out.println("Caught Exception: " + e.getMessage());
            System.out.println(e.getCause());
        }
        return null;
    }


    public void handleSelectedOption( int selectedOption ){
        
       
        switch (selectedOption){
            case 1 -> addCompanyHandler();
            case 2 -> addApplicationHandler();
            case 3 -> System.out.println(this.service.getCompanies());
            case 11 -> deleteCompanyHandler();
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

    private JobType getJobType( String argument ){

        int level = getInputInt(argument);
        for (JobType type : JobType.values()){
            if ( type.getWorkingHours() == level ) return type;
        }
        return null;
    }
 
    private ApplicationStatus getApplicationStatus(String argument){

        for ( ApplicationStatus status : ApplicationStatus.values() ){
            if ( status.getLevel() == getInputInt(argument) ) {
                return status;
            }
        }
        return null;
    }

    private LocalDate getInputDate(String text){
        System.out.println(map.get(text));
        return LocalDate.parse(this.scanner.next());
    }

    private void addApplicationHandler(){

        this.service.addApplication(getInputInt("CompanyId"), 
            getInput("roleTitle"),
            getJobType("jobType"),
            getApplicationStatus("status"),
            getInputDate(getInput("dateApplied")),
            getInputDate("followUpDate"),
            getInput("notes")
        );
    }

}
