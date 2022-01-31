package com.random.controllers;

import com.random.DAO.StudentDAO;
import com.random.entity.Student;
import com.random.helperClass.RandomHelper;
import com.random.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/random")
public class SecondPageController {

    StudentService studentService;
//todo пеернести в сервис то, что нужно в сервис

    @Autowired
    public SecondPageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/studentsLesson")
    public String secondPage(Model model) {
        List<Student> allStudents = studentService.getAllStudents2();
        model.addAttribute("students", allStudents);
        return "studentsOnLesson";
    }

    @GetMapping("/secondMainPage")
    public String secondMainPage( Model model) {
        List<Student> allStudents = studentService.getRandomStudents();
        model.addAttribute("students", allStudents);
        return "studentsOnLesson";
    }


    @GetMapping("/student")
    public String findStudent() {
        return "redirect:/random/studentsLesson";
    }
}
