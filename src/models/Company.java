package src.models;

import java.net.URI;

public class Company {
    private final int id;
    private String name;
    private String description;
    private URI url;
    private int numberOfEmployees;


    public Company(int id, String name, String description, URI url, int numberOfEmployees) {

        if ( id < 0 ) {
            throw  new IllegalArgumentException("id cannot be negative." );
        }

        if ( name == null || name.trim().isBlank() ){
            throw new IllegalArgumentException("Name cannot be null or blank." );
        }

        if ( !isValidURL(url) ) {
            throw new IllegalArgumentException("url cannot be null." );
        } 
        if (numberOfEmployees < 0 ) {
            throw new IllegalArgumentException("number of employees cannot be negative." );
        }
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.numberOfEmployees = numberOfEmployees;
        }

    private static boolean isValidURL(URI url){
        return  ( url != null && 
            url.getScheme() != null && 
            !url.getScheme().isBlank() && 
            url.getAuthority() != null && 
            !url.getAuthority().isBlank());
        
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

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
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
