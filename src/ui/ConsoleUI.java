package src.ui;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import src.models.*;
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
        map.put("ApplicationId","Enter application id: ");
        map.put("roleTitle", "Enter job title: ");
        map.put("jobType", "Enter working hours 120, 10, 80, 40: ");
        map.put("status", """ 
                                0: APPLIED
                                1: INTERVIEW
                                2: OFFER
                                3: REJECTED
                                4: ACCEPTED
                                5: WITHDRAWN
                                Enter application status: """ 
                            );
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
        print(OutputColor.YELLOW,"""
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
        print(OutputColor.YELLOW, map.get(argument));
        String input = this.scanner.next();
        return (input != null && !input.isBlank() ) ? input : getInput(argument) ;
    }

    private int getInputInt(String argument){
        print(OutputColor.YELLOW, map.get(argument));
        int input = this.scanner.nextInt();
        return (input > 0 ) ? input : getInputInt(argument);
    }
    
    private JobType getJobType( String argument ){

        int level = getInputInt(argument);
        for (JobType type : JobType.values()){
            if ( type.getWorkingHours() == level ){
                return type;
            } 
        }
        return null;
    }

    private ApplicationStatus getApplicationStatus( String argument ){
        int workingHours = getInputInt(argument);
        for ( ApplicationStatus status : ApplicationStatus.values() ){
            if ( status.getLevel() == workingHours ) {
                return status;
            }
        }
        return null;
    }

    private LocalDate getInputDate(String argument){
        print(OutputColor.GREEN, map.get(argument));
        LocalDate input = LocalDate.parse(this.scanner.next());
        return (input != null ) ? input : getInputDate(argument);
    }

    private void addCompanyHandler() {
        
        try {
            Company company = this.service.addCompany(
                getInput("Cname"),
                getInput("description"),
                getInput("url"),
                getInputInt("n"));
            if( company == null ) {
                print(OutputColor.RED, "Company could not be created !!!\n");
            }else{
                print(OutputColor.GREEN, "Sucessfully created company:\n");
                print(OutputColor.YELLOW, service.findCompanyById(company.getId()).toString()+"\n");
            }
        } catch (NumberFormatException e) {
            print(OutputColor.RED, "Company could not be created !!!\n" + e.getMessage());
        }
    }


    public void handleSelectedOption( int selectedOption ){
        
       
        switch (selectedOption){
            case 1 :
                 addCompanyHandler();
                 break;
            case 2 :
                 addApplicationHandler();
                 break;
            case 3 :
                 print(OutputColor.GREEN,this.service.getCompanies().toString());
                 break;
            case 4 :
                 print(OutputColor.GREEN, service.getApplications().toString());
                 break;
            case 5 :
                 print(OutputColor.GREEN, service.findCompanyById(getInputInt("CompanyId")).toString());
                 break;
            case 6 :
                 print(OutputColor.GREEN, service.findApplicationById(getInputInt("ApplicationId")).toString());
                 break;
            case 7 :
                 print(OutputColor.GREEN, service.listApplicationsByCompanyId(getInputInt("CompanyId")).toString());
                 break;
            case 8 :
                 print(OutputColor.GREEN, service.listApplicationsByStatus(getApplicationStatus("status")).toString());
                 break;
            case 9 :
                 service.updateApplicationStatus(getInputInt("ApplicationId"), getApplicationStatus("status"));
                 break;            
            case 10 :
                 print(OutputColor.RED, "Not implemented yet !!!");
                 break;
            case 11 :
                 this.service.deleteCompanyById(getInputInt("CompanyId"));
                 break;
            case 12 :
                 service.deleteApplication(getInputInt("ApplicationId"));
                 break;
            case 13 :
                 exit();
                 break;
        }
        
        

    }

    private  void exit(){
        try {
            scanner.close();
            print(OutputColor.BLUE, "Exiting Application ...");
            this.isClosed = true;
        } catch (Exception e) {
            print(OutputColor.RED, e.getMessage());
        }
        
    }


    
 
    

    

    private void addApplicationHandler(){

        this.service.addApplication(getInputInt("CompanyId"), 
            getInput("roleTitle"),
            getJobType("jobType"),
            getApplicationStatus("status"),
            getInputDate("dateApplied"),
            getInputDate("followUpDate"),
            getInput("notes")
        );
    }

    private void print(OutputColor color, String text){
        System.out.print(color.getCode() + text + OutputColor.BLACK.getCode() );
    }



}
