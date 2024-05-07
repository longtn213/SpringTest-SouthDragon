package com.southdragon.springmvc.repository;

import com.southdragon.springmvc.models.HistoryGrade;
import com.southdragon.springmvc.models.ScienceGrade;
import org.springframework.data.repository.CrudRepository;

public interface HistoryGradeDao extends CrudRepository<HistoryGrade, Integer> {

    public Iterable<HistoryGrade> findGradeByStudentId(int id);

    public void deleteByStudentId(int id);
}
