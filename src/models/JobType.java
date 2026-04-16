package src.models;

public enum JobType {
   
    
    FULL_TIME(120),
    PART_TIME(80),
    WORKING_STUDENT(80),
    INTERNSHIP(120);    

    private int workingHours;
    
    JobType(int workingHours){
        this.workingHours = workingHours;
    }

    public int getWorkingHours(){
        return this.workingHours;
    }



    
}
