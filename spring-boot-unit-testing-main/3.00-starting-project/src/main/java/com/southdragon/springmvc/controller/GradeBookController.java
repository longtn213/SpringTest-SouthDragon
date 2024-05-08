package com.southdragon.springmvc.controller;

import com.southdragon.springmvc.models.CollegeStudent;
import com.southdragon.springmvc.models.GradeBook;
import com.southdragon.springmvc.models.GradeBookCollegeStudent;
import com.southdragon.springmvc.service.StudentAndGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeBookController {

    @Autowired
    private GradeBook gradebook;

    @Autowired
    private StudentAndGradeService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getStudents(Model m) {
        Iterable<CollegeStudent> collegeStudents = service.getGradeBook();
        m.addAttribute("student", collegeStudents);

        return "index";
    }

    @PostMapping(value = "/")
    public String createStudent(@ModelAttribute("student") CollegeStudent collegeStudent, Model m){
        service.createStudent(collegeStudent.getFirstname(), collegeStudent.getLastname(), collegeStudent.getEmailAddress());

        Iterable<CollegeStudent> collegeStudents = service.getGradeBook();
        m.addAttribute("students", collegeStudents);
        return "index";
    }

    @GetMapping("delete/student/{id}")
    public String deleteStudent(@PathVariable int id, Model m){

        if(!service.checkIfStudentIsNull(id)){
            return "error";
        }

        service.deleteStudent(id);
        Iterable<CollegeStudent> collegeStudents = service.getGradeBook();
        m.addAttribute("students", collegeStudents);
        return "index";
    }

    @GetMapping("/studentInformation/{id}")
    public String studentInformation(@PathVariable int id, Model m) {
        if (!service.checkIfStudentIsNull(id)) return "error";

       service.configureStudentInformationModel(id, m);

        return "studentInformation";
    }

    @PostMapping(value = "/grades")
    public String createGrade(@RequestParam("grade") double grade, @RequestParam("gradeType") String gradeType, @RequestParam("studentId") int studentId, Model m){
        if(!service.checkIfStudentIsNull(studentId)) return "error";

        boolean success = service.createGrade(grade,studentId, gradeType);

        if(!success){
            return "error";
        }
        service.configureStudentInformationModel(studentId, m);

        return "studentInformation";

    }

    @GetMapping("/grades/{id}/{gradeType}")
    public String deleteGrade(@PathVariable int id, @PathVariable String gradeType, Model m){

        int studentId = service.deleteGrade(id, gradeType);
        if(studentId == 0){
            return "error";
        }

        service.configureStudentInformationModel(studentId, m);
        return "studentInformation";
    }

}
