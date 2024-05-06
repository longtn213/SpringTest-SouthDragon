package com.southdragon.springmvc.service;

import com.southdragon.springmvc.models.CollegeStudent;
import com.southdragon.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {

    @Autowired
    private StudentDao studentDao;

    public void createStudent(String firstname, String lastname, String emailAddress){

        CollegeStudent student = new CollegeStudent(firstname,lastname,emailAddress);
        student.setId(0);
        studentDao.save(student);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        return student.isPresent();
    }

    public void deleteStudent(int id) {
        if(checkIfStudentIsNull(id)) studentDao.deleteById(1);
    }

    public Iterable<CollegeStudent> getGradeBook() {
        return studentDao.findAll();

    }
}
