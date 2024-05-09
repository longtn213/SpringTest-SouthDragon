package com.southdragon.springmvc.repository;

import com.southdragon.springmvc.models.MathGrade;
import com.southdragon.springmvc.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

public interface ScienceGradeDao extends CrudRepository<ScienceGrade, Integer> {

     public Iterable<ScienceGrade> findGradeByStudentId(int id);
     public void deleteByStudentId(int id);
}
