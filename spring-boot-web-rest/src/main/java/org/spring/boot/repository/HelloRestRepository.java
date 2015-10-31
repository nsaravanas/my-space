package org.spring.boot.repository;

import org.spring.boot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRestRepository extends CrudRepository<User, Integer> {

}