package com.southdragon.springmvc.repository;

import com.southdragon.springmvc.models.CollegeStudent;
import org.springframework.data.repository.CrudRepository;

public interface StudentDao extends CrudRepository<CollegeStudent, Integer> {
    public CollegeStudent findByEmailAddress(String emailAddress);
}
