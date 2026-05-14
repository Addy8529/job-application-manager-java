package com.mahad.jobmanager.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mahad.jobmanager.models.NewCompany;
import com.mahad.jobmanager.repository.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    private CompanyController( CompanyRepository companyRepository ){
        this.companyRepository = companyRepository;
    }

    @GetMapping
    protected ResponseEntity<Iterable<NewCompany>> getAllCompanies(){

        return ResponseEntity.ok(companyRepository.findAll());
    }

    @GetMapping("/{id}")
    protected ResponseEntity<NewCompany> findCompanyById( @PathVariable("id") @NonNull Integer id){

        Optional<NewCompany> company = companyRepository.findById(id);
        if (company.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(company.get());
    }

    @PostMapping
    protected ResponseEntity<Void> addCompany( @RequestBody @NonNull NewCompany company){
        NewCompany newCompany = companyRepository.save(company);
        Integer id = newCompany.getId();
        if (id != null){
            return ResponseEntity.created(URI.create("/company/" + id)).build();
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    private ResponseEntity<Void> updateCompany( @PathVariable("id") @NonNull Integer id, @RequestBody @NonNull NewCompany newCompany){
        if( companyRepository.findById(id).isEmpty()){

            return ResponseEntity.notFound().build();

        }else{
            NewCompany company = new NewCompany(
                id,
                newCompany.getName(),
                newCompany.getDescription(),
                newCompany.getUrl(),
                newCompany.getNumberOfEmployees()
            );

            companyRepository.save(company);
            return ResponseEntity.noContent().build();
        }


    }

    @DeleteMapping("/{id}")
    protected ResponseEntity<Void> deleteCompany( @PathVariable("id") @NonNull Integer id ){
        companyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
