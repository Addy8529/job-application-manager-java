package com.mahad.jobmanager.models;

//import com.mahad.jobmanager.repository.NewCompany;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;

@Entity
public class Application{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @NonNull
    private String title;

    public Application() {
        
    }
    public Application(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    //@ManyToOne
    //@JoinColumn(name = "company_id", nullable = false)
    //private NewCompany company;
}
