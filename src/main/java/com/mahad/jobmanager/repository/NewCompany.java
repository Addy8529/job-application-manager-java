package com.mahad.jobmanager.repository;

import org.springframework.data.annotation.Id;

public record NewCompany (@Id Integer id, String name, String description, String url, Integer numberOfEmployees){

}

