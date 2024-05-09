package com.southdragon.springmvc;

import com.southdragon.springmvc.models.*;
import com.southdragon.springmvc.repository.HistoryGradeDao;
import com.southdragon.springmvc.repository.MathGradeDao;
import com.southdragon.springmvc.repository.ScienceGradeDao;
import com.southdragon.springmvc.repository.StudentDao;
import com.southdragon.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class StudentAndGradeServiceTest {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private StudentAndGradeService studentService;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private MathGradeDao mathGradeDao;

    @Autowired
    private ScienceGradeDao scienceGradeDao;

    @Autowired
    private HistoryGradeDao historyGradeDao;

    @Value("${sql.scripts.create.student}")
    private String sqlAddStudent;

    @Value("${sql.scripts.create.math.grade}")
    private String sqlAddMathGrade;

    @Value("${sql.scripts.create.science.grade}")
    private String sqlAddScienceGrade;

    @Value("${sql.scripts.create.history.grade}")
    private String sqlAddHistoryGrade;

    @Value("${sql.script.delete.student}")
    private String sqlDeleteStudent;

    @Value("${sql.script.delete.math.grade}")
    private String sqlDeleteMathGrade;

    @Value("${sql.script.delete.science.grade}")
    private String sqlDeleteScienceGrade;

    @Value("${sql.script.delete.history.grade}")
    private String sqlDeleteHistoryGrade;

    @BeforeEach
    public void setupDatabase(){
        jdbc.execute(sqlAddStudent);
        jdbc.execute(sqlAddMathGrade);
        jdbc.execute(sqlAddScienceGrade);
        jdbc.execute(sqlAddHistoryGrade );
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
        Optional<MathGrade>     deletedMathGrade = mathGradeDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade= scienceGradeDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade= historyGradeDao.findById(1);

        assertTrue(deleteCollegeStudent.isPresent(), "Return true");
        assertTrue(deletedMathGrade.isPresent());
        assertTrue(deletedScienceGrade.isPresent());
        assertTrue(deletedHistoryGrade.isPresent());

        studentService.deleteStudent(1);

        deleteCollegeStudent = studentDao.findById(1);
        deletedMathGrade = mathGradeDao.findById(1);
        deletedScienceGrade= scienceGradeDao.findById(1);
        deletedHistoryGrade= historyGradeDao.findById(1);


        assertFalse(deleteCollegeStudent.isPresent(),"Return False");
        assertFalse(deletedMathGrade.isPresent(),"Return False");
        assertFalse(deletedScienceGrade.isPresent(),"Return False");
        assertFalse(deletedHistoryGrade.isPresent(),"Return False");

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

    @Test
    public void createGradeService(){
        //create the grade
        assertTrue(studentService.createGrade(80.50, 1, "math"));
        assertTrue(studentService.createGrade(80.50, 1, "science"));
        assertTrue(studentService.createGrade(80.50, 1, "history"));

        //get all grades with studentId
        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(1);

        //verify there is grades
        assertTrue(((Collection<MathGrade>) mathGrades).size() == 2,
                "Student has math grades");
        assertTrue(((Collection<ScienceGrade>) scienceGrades).size() == 2, "Student has science grades");
        assertTrue(((Collection<HistoryGrade>) historyGrades).size() == 2, "Student has history grades");
    }

    @Test
    public void createGradeServiceReturnFalse(){
        assertFalse(studentService.createGrade(105,1,"math"));
        assertFalse(studentService.createGrade(-5,1,"science"));
        assertFalse(studentService.createGrade(85,2,"history"));
        assertFalse(studentService.createGrade(85,1,"literature"));
    }

    @Test
    public void deleteGradeService(){
        assertEquals(1, studentService.deleteGrade(1, "math"), "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "history"), "Returns student id after delete");
        assertEquals(1, studentService.deleteGrade(1, "science"), "Returns student id after delete");

    }

    @Test
    public void deleteGradeServiceReturnStudentIdOfZero(){
        assertEquals(0 , studentService.deleteGrade(0, "science")
                ,"No student should have 0 id");
        assertEquals(0 , studentService.deleteGrade(1, "literature")
                ,"No student should have 0 id");

    }

    @Test
    public void studentInformation(){
        GradeBookCollegeStudent gradeBookCollegeStudent = studentService.studentInformation(1);

        assertNotNull(gradeBookCollegeStudent);
        assertEquals(1, gradeBookCollegeStudent.getId());
        assertEquals("Long", gradeBookCollegeStudent.getFirstname());
        assertEquals("Tran", gradeBookCollegeStudent.getLastname());
        assertEquals("123@gmail.com", gradeBookCollegeStudent.getEmailAddress());

        assertTrue(gradeBookCollegeStudent.getStudentGrades().getHistoryGradeResults().size() == 1);
        assertTrue(gradeBookCollegeStudent.getStudentGrades().getScienceGradeResults().size() == 1);
        assertTrue(gradeBookCollegeStudent.getStudentGrades().getMathGradeResults().size() == 1);
    }

    @Test
    public void studentInformationServiceReturnNull(){
        GradeBookCollegeStudent gradeBookCollegeStudent = studentService.studentInformation(0);

        assertNull(gradeBookCollegeStudent);
    }

    @AfterEach
    public void setupAfterTransaction(){
        jdbc.execute(sqlDeleteStudent);
        jdbc.execute(sqlDeleteMathGrade);
        jdbc.execute(sqlDeleteScienceGrade);
        jdbc.execute(sqlDeleteHistoryGrade);
    }
}
