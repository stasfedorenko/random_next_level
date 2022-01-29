package com.random.controllers;

import com.random.entity.Student;
import com.random.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/random")
public class MarkPageController {

    StudentService studentService;

    @Autowired
    public MarkPageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/marksStudent")
    public String saveMarkStudent(@RequestParam("id") int id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("studentMark", student);
        return "/markStudent";
    }

    @PostMapping("/markedStudent")
    public String markedStudent(@RequestParam("studentMark") int id, @RequestParam("f_mark") double f_mark,
                                @RequestParam("g_ansMark") double g_ansMark,
                                @RequestParam("k_qMark") double k_qMark) {
        Student student = studentService.getStudent(id);
        System.out.println(student);
        student.setF_mark(f_mark);
        student.setG_ansMark(g_ansMark);
        student.setK_qMark(k_qMark);
        studentService.saveStudent(student);
        return "redirect:/random/secondMainPage";
    }
}
