package src.services;

import java.util.ArrayList;
import java.util.Iterator;

import src.models.Company;
import src.models.JobApplication;

public class ApplicationService {
    
    private ArrayList<Company> companies;
    private ArrayList<JobApplication> applications;
    
    public ApplicationService(){
        this.companies = new ArrayList<>();
        this.applications = new ArrayList<>();
    }

    public void addCompany(Company company){

        if(this.companies.contains(company)){
            throw new IllegalArgumentException("Company already exists");
        }else{
            this.companies.add(company); 
        }
        
    }

    public void addApplication(JobApplication app){
        if(this.applications.contains(app)){
            System.out.println("Application already exists in the database.");
        }else{
            this.applications.add(app);
        }
        
    }

    public ArrayList<Company> getCompanies(){
        return this.companies;
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
