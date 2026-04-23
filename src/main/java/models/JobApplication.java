package src.main.java.models;

import java.time.LocalDate;

public class JobApplication {

    private final int id;
    private final int companyId;
    private final String roleTitle;
    private final JobType jobType;
    private ApplicationStatus status;
    private final LocalDate dateApplied;
    private LocalDate followUpDate;
    private String notes;

    public JobApplication(int id,int companyId, String roleTitle, JobType jobType, ApplicationStatus status,
            LocalDate dateApplied, LocalDate followUpDate, String notes) {
        
            if ( id < 0 ) {
                throw new IllegalArgumentException("id cannot be negative.");
            }
                
            if ( companyId < 0 ) {
                throw new IllegalArgumentException("companyId cannot be negative.");
            }
            
            if ( !isRoleTitleValid(roleTitle) ) {
                throw new IllegalArgumentException("roleTitle cannot be null or blank.");
            }
            
            if ( jobType == null ) {
                throw new IllegalArgumentException("JobType cannot be null.");
            }
            
            if ( dateApplied == null ) {
                throw new IllegalArgumentException("dateApplied cannot be null.");
            }

            LocalDate resolvedFollowUpDate = ( followUpDate != null ) 
                ? followUpDate 
                : dateApplied.plusDays(7); 
            
            if ( resolvedFollowUpDate.isBefore(dateApplied) ) {
                throw new IllegalArgumentException("Follow-up date cannot be before application date");
            }
                
            this.id = id;
            this.companyId = companyId;
            this.roleTitle = roleTitle.trim();
            this.jobType = jobType;
            this.status = ( status == null ) ? ApplicationStatus.APPLIED : status;
            this.dateApplied = dateApplied;
            this.followUpDate = resolvedFollowUpDate;
            this.notes = (notes == null || notes.isBlank()) ? "No notes." : notes.trim();

             
    }
    
    public JobApplication(int id, int companyId, String roleTitle, JobType jobType) {
        
        if ( id < 0 ) {
            throw new IllegalArgumentException("id cannot be negative.");
        }

        if ( companyId < 0 ) {
            throw new IllegalArgumentException("companyId cannot be negative.");
        }

        if ( !isRoleTitleValid(roleTitle) ) {
            throw new IllegalArgumentException("roleTitle cannot be null or blank.");
        }

        if ( jobType == null ) {
            throw new IllegalArgumentException("JobType cannot be null.");
                }

        this.id = id;
        this.companyId = companyId;
        this.roleTitle = roleTitle.trim();
        this.jobType = jobType;
        this.dateApplied = LocalDate.now();
        this.status = ApplicationStatus.APPLIED;
        this.followUpDate = this.dateApplied.plusDays(7);
        this.notes = "No notes.";
    }

    private boolean isRoleTitleValid(String roleTitle){
        return ( roleTitle != null && !roleTitle.trim().isBlank() );  
    }

    public int getId() {
        return this.id;
    }

    public String getRoleTitle(){
        return this.roleTitle;
    }

    public int getCompanyId() {
        return this.companyId;
    }

    public JobType getJobType() {
        return this.jobType;
    }

    public ApplicationStatus getStatus() {
        return this.status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return this.dateApplied;
    }

    public LocalDate getFollowUpDate() {
        return this.followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean hasFollowUpDue(){
        return this.followUpDate != null && !this.followUpDate.isAfter(LocalDate.now());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JobApplication other = (JobApplication) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + roleTitle +
       " | CompanyId: " + companyId +
       " | Type: " + jobType +
       " | Status: " + status +
       " | Applied: " + dateApplied +
       " | Follow-up: " + followUpDate+
       " | Notes: "+ notes;
    }

    

}