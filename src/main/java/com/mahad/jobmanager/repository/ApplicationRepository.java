package com.mahad.jobmanager.repository;

import org.springframework.data.repository.CrudRepository;
import com.mahad.jobmanager.models.Application;

public interface ApplicationRepository extends CrudRepository< Application, Integer> {}
