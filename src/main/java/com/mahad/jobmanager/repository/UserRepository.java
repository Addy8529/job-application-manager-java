package com.mahad.jobmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.mahad.jobmanager.models.User;

public interface UserRepository extends CrudRepository< User, Integer>{

}
