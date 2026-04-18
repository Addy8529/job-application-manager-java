package src.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.NameNotFoundException;

import java.net.URI;
import src.models.ApplicationStatus;
import src.models.Company;
import src.models.JobApplication;
import src.models.JobType;


public class ApplicationService {
    
    private ArrayList<Company> companies;
    private ArrayList<JobApplication> applications;
    private int nextCompanyId;
    private int nextApplicationId;

    public ApplicationService(){
        this.companies = new ArrayList<>();
        this.applications = new ArrayList<>();
        this.nextCompanyId = 0;
        this.nextApplicationId = 0;
    }

    public Company addCompany( String name, String description, URI url, int numberOfEmployees ){
        
        if ( nextCompanyId < 0 ) {
            throw new InternalError("nextCompanyId was negative." );
        }

        try{
            Company company = new Company(nextCompanyId, name, description, url, numberOfEmployees);
            
            nextCompanyId++;
            this.companies.add(company);
            return company;
        }catch (IllegalArgumentException e){
            System.out.println("Caugth Exception: " + e.getMessage() );
        }

        return null;
    }
    

    public JobApplication addApplication(
        int companyId, 
        String roleTitle, 
        JobType jobType, 
        ApplicationStatus status, 
        LocalDate dateApplied, 
        LocalDate followUpDate, 
        String notes){
            
            try{
                int id = nextApplicationId;

                if ( id < 0 ){
                    throw new InternalError("nextApplication id was negative.");
                }

                if ( companyId < 0 || this.findCompanyById(companyId) == null  ) {
                    throw new IllegalArgumentException("companyId is negative or does not exist." );
                }

                nextApplicationId++;

                JobApplication application = new JobApplication(
                    id,
                    companyId, 
                    roleTitle,
                    jobType, 
                    status, 
                    dateApplied, 
                    followUpDate, 
                    notes
                );
                this.applications.add(application);

                return application;
            }catch (IllegalArgumentException e ) {
                System.out.println(e.getMessage());
            }



        return null;
    }

    public ArrayList<Company> getCompanies(){
        return new ArrayList<>(this.companies);
    }

    public ArrayList<JobApplication> getApplications(){
        return new ArrayList<>(this.applications);
    }
    
    public Company findCompany(String name){
        Company result = null;
        Iterator<Company> iterator = this.companies.iterator();

        while(iterator.hasNext()){
            result = iterator.next();
            if(result.getName().equals(name)){
                return result;
            }
        }
        return result;
    }

    public Company findCompanyById(int id){
        
        for (Company company : companies){

            if( company.getId() == id){
                return company;
            }
        }
        return null;
    }

    public JobApplication findApplicationById(int id){
        
        if ( id < 0 ) {
            throw new IllegalArgumentException("id cannot be negative." );
        }

        for ( JobApplication app : applications ) {
            if ( id == app.getId() ) {
                return app;
            }
        }
        return null;
    }

    public JobApplication findApplication(String roletitel){
        JobApplication result= null;
        Iterator<JobApplication> iterator = this.applications.iterator();

        while (iterator.hasNext()) {
            result = iterator.next();

            if(result.getRoleTitle().equals(roletitel)){
                return result;
            }
        }
        return result;
    }

    public ArrayList<JobApplication> listApplicationsByCompanyId(int companyId){
        ArrayList<JobApplication> list =  new ArrayList<>();

        if ( companyId < 0){
            throw new IllegalArgumentException("id cannot be negative." );
        }

        for ( JobApplication app : this.applications ) {
            if ( app.getCompanyId() == companyId ){
                list.add(app);
            }
        }
        return list;
    }

    public ArrayList<JobApplication> listApplicationsByStatus(ApplicationStatus status){

        if ( status == null ) {
            throw new IllegalArgumentException("status cannot be null." );
        }

        ArrayList<JobApplication> list = new ArrayList<>();

        for ( JobApplication app : this.applications ) {

            if ( app.getStatus().equals(status) ){
                list.add(app);
            }
        }
        return list;
    }

    public void deleteApplication(int id){
        this.applications.remove(findApplicationById(id));
    }

    public void deleteCompanyById(int id){
        
        if ( id < 0 ) {
            throw new IllegalArgumentException("id cannot be negative." );
        }

        for ( JobApplication app : listApplicationsByCompanyId(id)) {
            deleteApplication(app.getId());
        }

        this.companies.remove(findCompanyById(id));

    }

    @Override
    public String toString() {
        String result = "";
        Iterator<Company> iterator = this.companies.iterator();

        while (iterator.hasNext()) {
            result = result.concat(iterator.next().toString()+"\n");
        }
        Iterator<JobApplication> iterator2 = this.applications.iterator();

        while (iterator2.hasNext()) {
            result = result.concat(iterator2.next().toString()+"\n");
        }

        return result;
    }

    

    
    
}
