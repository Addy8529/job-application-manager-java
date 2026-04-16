package src.models;

public class Company {
    private final int id;
    private String name;
    private String description;
    private String url;
    private int numberOfEmployees;


    public Company(final int id,final String name, final String description, final String url, final int numberOfEmployees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.numberOfEmployees = numberOfEmployees;
        }

    public Company(final int id, final String name, final String description, final int numberOfEmployees) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfEmployees = numberOfEmployees;
        }
    
    public Company(final int id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Company(final int id, final String name, final int numberOfEmployees) {
        this.id = id;
        this.name = name;
        this.numberOfEmployees = numberOfEmployees;
    }

    public Company(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return this.id;
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
        return "ID: "+this.getId() + 
        " | Name: "+this.getName()+
        " | Url: "+this.getUrl()+
        " | Description: "+this.getDescription();
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
        Company other = (Company) obj;
        if (id != other.id)
            return false;
        return true;
    }

    

   

    
    
    

    



    
}
