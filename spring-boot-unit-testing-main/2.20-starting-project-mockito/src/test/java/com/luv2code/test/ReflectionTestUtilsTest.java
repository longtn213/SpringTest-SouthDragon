package com.luv2code.test;

import com.southdragon.component.MvcTestingExampleApplication;
import com.southdragon.component.models.CollegeStudent;
import com.southdragon.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ReflectionTestUtilsTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void studentBeforeEach(){
        studentOne.setFirstname("Long");
        studentOne.setLastname("Tran");
        studentOne.setEmailAddress("123@gmail.com");
        studentOne.setStudentGrades(studentGrades);

        ReflectionTestUtils.setField(studentOne,"id", 1);
        ReflectionTestUtils.setField(studentOne,"studentGrades",
                    new StudentGrades(new ArrayList<>(Arrays.asList(100.0, 85.0)))
                );


    }

    @Test
    public void getPrivatedField(){
        assertEquals(1, ReflectionTestUtils.getField(studentOne,"id"));
    }

    @Test
    public void invokePrivateMethod(){
        assertEquals("Long 1",
            ReflectionTestUtils.invokeMethod(studentOne,"getFirstNameAndId"),
                "Fail private method not call");
    }
}
