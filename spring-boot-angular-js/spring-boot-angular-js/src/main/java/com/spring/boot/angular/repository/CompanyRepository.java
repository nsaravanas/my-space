package com.spring.boot.angular.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.angular.model.Company;

@Repository
public interface CompanyRepository extends MongoRepository<Company, Integer> {

}
