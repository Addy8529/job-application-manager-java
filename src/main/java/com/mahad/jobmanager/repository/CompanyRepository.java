package com.mahad.jobmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahad.jobmanager.models.Company;

interface CompanyRepository extends CrudRepository<Company, Integer> {

}
