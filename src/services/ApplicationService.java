package src.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

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

    public Company addCompany(String name, String description, String url, int numberOfEmployees ){
        Company result= null;
        
        if( name == null || name.isBlank() ){
            throw new IllegalArgumentException("Company name must not be null or blank.");
        }else{
            for(Company company: companies){
                if(company.getName().equalsIgnoreCase(name.trim())){
                    throw new IllegalArgumentException("Company name already exists.");
                }
            }
            result = new Company(nextCompanyId, name.trim(), description, url, numberOfEmployees);
            nextCompanyId++;
            companies.add(result);
        }
        return result;
    }

    public JobApplication addApplication(int companyId, String roleTitle, JobType type, LocalDate dateApplied, LocalDate followUpDate, String notes ){
        
            if ( this.findCompanyById(companyId).equals(null)){
                throw new IllegalArgumentException("No company associated with the given company id.");
            }else{
                String normalizedRoleTitel = roleTitle.trim();
                if( normalizedRoleTitel == null || normalizedRoleTitel.isBlank() ){
                    throw new IllegalArgumentException("Role Title cannot be null or blank.");
                }else{
                    JobApplication app = new JobApplication(nextApplicationId, companyId, roleTitle, type, dateApplied, followUpDate,notes);
                    nextApplicationId++;
                    this.applications.add(app);
                    return app;
                }
            }
        
    }

    public ArrayList<Company> getCompanies(){
        return new ArrayList<>(this.companies);
    }

    public ArrayList<JobApplication> getApplications(){
        return this.applications;
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
