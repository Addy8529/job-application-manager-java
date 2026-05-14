package com.mahad.jobmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahad.jobmanager.models.NewCompany;


public interface CompanyRepository extends CrudRepository<NewCompany, Integer> {

}
