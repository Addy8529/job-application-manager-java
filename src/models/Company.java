package src.models;

public class Company {

    private String name;
    private String description;
    private String url;
    private int numberOfEmployees;


    public Company(final String name, final String description, final String url, final int numberOfEmployees) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.numberOfEmployees = numberOfEmployees;
        }

    public Company(final String name, final String description, final int numberOfEmployees) {
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        }
    
    public Company(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public Company(final String name, final int numberOfEmployees) {
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Company(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(final int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    @Override
    public String toString() {
        return "Company [name=" + name + ", description=" + description + ", url=" + url + ", numberOfEmployees="
                + numberOfEmployees + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Company other = (Company) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

   

    
    
    

    



    
}
