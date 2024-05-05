package com.luv2code.test;

import com.southdragon.component.MvcTestingExampleApplication;
import com.southdragon.component.dao.ApplicationDao;
import com.southdragon.component.models.CollegeStudent;
import com.southdragon.component.models.StudentGrades;
import com.southdragon.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

    //    @Mock
    @MockBean
    private ApplicationDao applicationDao;

    //    @InjectMocks
    @Autowired
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach() {
        studentOne.setFirstname("long");
        studentOne.setLastname("Tran");
        studentOne.setEmailAddress("123@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When & Verify")
    @Test
    public void assertEqualsTestGrade() {
        when(applicationDao.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults())).thenReturn(100.0);

        assertEquals(100, applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()));
        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
        verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @DisplayName("Find Gpa")
    @Test
    public void assertEqualsFindGpa() {
        when(applicationDao.findGradePointAverage(
                studentGrades.getMathGradeResults())).thenReturn(88.1);

        assertEquals(88.1, applicationService.findGradePointAverage(
                studentOne.getStudentGrades().getMathGradeResults()));
        verify(applicationDao).findGradePointAverage(studentGrades.getMathGradeResults());
        verify(applicationDao, times(1)).findGradePointAverage(studentGrades.getMathGradeResults());
    }

    @DisplayName("Not null")
    @Test
    public void testAssertNotNull() {
        when(applicationDao.checkNull(
                studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(
                studentOne.getStudentGrades().getMathGradeResults()), "Object should not be null");

    }

    @DisplayName("Throw run time exception")
    @Test
    public void throwRunTimeError() {
        CollegeStudent studentNull = (CollegeStudent) context.getBean("collegeStudent");

        doThrow(new RuntimeException()).when(applicationDao).checkNull(studentNull);

        assertThrows(RuntimeException.class,()-> applicationService.checkNull(studentNull));

        verify(applicationDao, times(1)).checkNull(studentNull);

    }

    @DisplayName("Multiple Stubbing")
    @Test
    public void stubbingConsecutiveCalls() {
        CollegeStudent studentNull = (CollegeStudent) context.getBean("collegeStudent");

        when(applicationDao.checkNull(studentNull)).thenThrow(new RuntimeException()).thenReturn("Do not throw exception second time");
        assertThrows(RuntimeException.class,()-> applicationService.checkNull(studentNull));

        assertEquals("Do not throw exception second time", applicationService.checkNull(studentNull));

        verify(applicationDao, times(2)).checkNull(studentNull);

    }
}
