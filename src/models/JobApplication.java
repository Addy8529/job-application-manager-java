package src.models;

import java.time.LocalDate;

public class JobApplication {

    private final int id;
    private final Company company;
    private final String roleTitle;
    private final JobType jobType;
    private ApplicationStatus status;
    private final LocalDate dateApplied;
    private LocalDate followUpDate;
    private String notes;

    public JobApplication(int id, Company company, String roleTitle, JobType jobType, ApplicationStatus status,
            LocalDate dateApplied, LocalDate followUpDate, String notes) {
        this.id = id;
        this.company = company;
        this.roleTitle = roleTitle;
        this.jobType = jobType;
        this.status = status;
        this.followUpDate = followUpDate;
        this.notes = notes;
        this.dateApplied = dateApplied;
        
    }
    
    public JobApplication(int id, Company company, String roleTitle, JobType jobType) {
        this.id = id;
        this.company = company;
        this.roleTitle = roleTitle;
        this.jobType = jobType;
        this.dateApplied = LocalDate.now();
        this.status = ApplicationStatus.APPLIED;
        this.followUpDate = LocalDate.of(this.dateApplied.getYear(),this.dateApplied.getMonth(),this.dateApplied.getDayOfMonth()+7);
        this.notes = "No notes.";
    }

    public int getId() {
        return id;
    }


    public Company getCompany() {
        return company;
    }


    public JobType getJobType() {
        return jobType;
    }


    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public LocalDate getDateApplied() {
        return dateApplied;
    }


    public LocalDate getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((roleTitle == null) ? 0 : roleTitle.hashCode());
        result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
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
        if (company == null) {
            if (other.company != null)
                return false;
        } else if (!company.equals(other.company))
            return false;
        if (roleTitle == null) {
            if (other.roleTitle != null)
                return false;
        } else if (!roleTitle.equals(other.roleTitle))
            return false;
        if (jobType != other.jobType)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JobApplication [id=" + id + ", company=" + company.getName() + ", roleTitle=" + roleTitle + ", jobType=" + jobType
                + ", status=" + status + ", dateApplied=" + dateApplied + ", followUpDate=" + followUpDate + ", notes="
                + notes + "]";
    }

    

    
    


}