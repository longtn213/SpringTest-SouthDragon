package com.southdragon.springmvc.controller;

import com.southdragon.springmvc.models.CollegeStudent;
import com.southdragon.springmvc.models.GradeBook;
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
        return "studentInformation";
    }

}
