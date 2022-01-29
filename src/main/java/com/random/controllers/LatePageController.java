package com.random.controllers;

import com.random.entity.Student;
import com.random.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/random")
public class LatePageController {

    StudentService studentService;

    @Autowired
    public LatePageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/late")
    public String mainPage(Model model) {
        List<Student> allStudents = studentService.getAllLateStudents();
        model.addAttribute("allStudents2", allStudents);
        return "lateStudent";
    }

    @PostMapping("/returnsStudent")
    public String returnStudent(@RequestParam("id") int id) {
        Student student = studentService.getStudent(id);
        student.setE_isPresent(true);
        studentService.saveStudent(student);
        return "redirect:/random/late";
    }
}
