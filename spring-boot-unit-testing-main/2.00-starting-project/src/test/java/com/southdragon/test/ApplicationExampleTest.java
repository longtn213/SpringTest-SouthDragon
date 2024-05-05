package com.southdragon.test;

import com.southdragon.component.MvcTestingExampleApplication;
import com.southdragon.component.models.CollegeStudent;
import com.southdragon.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {

    private static int count = 0;

    @Value("${info.app.name}")
    private String appInfo;

    @Value("${info.app.description}")
    private String appDes;

    @Value("${info.app.version}")
    private String appVer;

    @Value("${info.school.name}")
    private String appSchool;

    @Autowired
    StudentGrades studentGrades;

    @Autowired
    CollegeStudent collegeStudent;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is: " + appDes +
                " Version: " + appVer + " .Execution of test method " + count);
        collegeStudent.setFirstname("Tran");
        collegeStudent.setLastname("Long");
        collegeStudent.setEmailAddress("Longtran123@gmail.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 85.0, 91.31)));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade results for student grades")
    @Test
    public void addGradeResultForStudentGrades(){
        assertEquals(276.31,
                studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }
    @DisplayName("Add grade results for student grades not equal")
    @Test
    public void addGradeResultForStudentGradesAssertNotEquals(){
        assertNotEquals(353.31,
                studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Is grade greater")
    @Test
    public void isGradeGreaterStudentGrades(){
        assertTrue(studentGrades.isGradeGreater(90, 40),
                "failure - should be true");
    }

    @DisplayName("Is grade greater false")
    @Test
    public void isGradeGreaterStudentGradesFalse(){
        assertFalse(studentGrades.isGradeGreater(90, 140),
                "failure - should be false");
    }

    @DisplayName("Check null for student grade")
    @Test
    public void checkNullForStudentGrades(){
        assertNotNull(studentGrades.checkNull(collegeStudent.getStudentGrades().getMathGradeResults()),
                "failure - should be not null");
    }


    @DisplayName("creat student without grade init")
    @Test
    public void createStudentWithoutGradeInit(){
        CollegeStudent student = context.getBean("collegeStudent", CollegeStudent.class);
        student.setFirstname("Hi");
        student.setLastname("Hello");
        student.setEmailAddress("123@gmail.com");
        assertNotNull(student.getFirstname());
        assertNotNull(student.getLastname());
        assertNotNull(student.getEmailAddress());
        assertNull(studentGrades.checkNull(student.getStudentGrades()));
    }


    @DisplayName("Verify student are prototypes")
    @Test
    public void verifyStudentArePrototypes(){
        CollegeStudent student = context.getBean("collegeStudent", CollegeStudent.class);

        assertNotSame(collegeStudent, student);
    }

    @DisplayName("Find Grade Point Average")
    @Test
    public void findGradePointAverage(){
        assertAll("Testing all assertEquals",
                () -> assertEquals(276.31, studentGrades.addGradeResultsForSingleClass(
                        collegeStudent.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(92.1, studentGrades.findGradePointAverage(
                        collegeStudent.getStudentGrades().getMathGradeResults()))

        );
    }
}
