package com.southdragon.springmvc;

import com.southdragon.springmvc.models.CollegeStudent;
import com.southdragon.springmvc.repository.StudentDao;
import com.southdragon.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    StudentAndGradeService studentService;

    @Autowired
    StudentDao studentDao;

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute("insert into student(id, firstname, lastname, email_address) " +
                "values (1, 'Long', 'Tran', '123@gmail.com')" );

    }


    @Test
    public void createStudentService(){

        studentService.createStudent("Long", "tran", "1234@gmail.com");

        CollegeStudent student = studentDao.findByEmailAddress("123@gmail.com");

        assertEquals("123@gmail.com", student.getEmailAddress(),"find by email");
    }

    @Test
    public void isStudentNullCheck(){
        assertTrue(studentService.checkIfStudentIsNull(1));

        assertFalse(studentService.checkIfStudentIsNull(0));
    }

    @Test
    public void deleteStudentService(){
        Optional<CollegeStudent> deleteCollegeStudent = studentDao.findById(1);

        assertTrue(deleteCollegeStudent.isPresent(), "Return true");

        studentService.deleteStudent(1);

        deleteCollegeStudent = studentDao.findById(1);
        assertFalse(deleteCollegeStudent.isPresent(),"Return False");

    }

//    execute beforeEach first then execute sql
    @Sql("/insertData.sql")
    @Test
    public void getGradeBookService(){
        Iterable<CollegeStudent> interableCollegeStudents = studentService.getGradeBook();

        List<CollegeStudent> collegeStudentList = new ArrayList<>();
        for (CollegeStudent collegeStudent : interableCollegeStudents){
            collegeStudentList.add(collegeStudent);
        }
        assertEquals(6, collegeStudentList.size());

    }


    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute("delete from student");
    }
}
