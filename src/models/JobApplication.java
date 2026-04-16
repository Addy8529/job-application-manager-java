package src.models;

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

    public JobApplication(int id,int companyId, String roleTitle, JobType jobType,
            LocalDate dateApplied, LocalDate followUpDate, String notes) {
        this.id = id;
        this.companyId = companyId;
        this.roleTitle = roleTitle;
        this.jobType = jobType;
        this.status = ApplicationStatus.APPLIED;
        
        this.notes = notes;
        this.dateApplied = dateApplied;

        if (followUpDate != null && followUpDate.isBefore(dateApplied)) {
            throw new IllegalArgumentException("Follow-up date cannot be before application date");
        }else{
            this.followUpDate = followUpDate;
        }
        
    }
    
    public JobApplication(int id, int companyId, String roleTitle, JobType jobType) {
        this.id = id;
        this.companyId = companyId;
        this.roleTitle = roleTitle;
        this.jobType = jobType;
        this.dateApplied = LocalDate.now();
        this.status = ApplicationStatus.APPLIED;
        this.followUpDate = this.dateApplied.plusDays(7);
        this.notes = (notes != null) ? notes : "No notes.";
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