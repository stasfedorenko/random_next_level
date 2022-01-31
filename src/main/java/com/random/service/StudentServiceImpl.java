package com.random.service;


import com.random.DAO.StudentDAO;
import com.random.entity.Student;
import com.random.helperClass.ExcelHelper;
import com.random.helperClass.RandomHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDAO studentDAO;
    List<Student> allStudents = new ArrayList<>();

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        List<Student> studentList = ExcelHelper.excelToStudent();
        studentDAO.saveAll(studentList);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAllisPresent();
    }

    @Override
    public List<Student> getAllStudents2() {
        List<Student> students = studentDAO.findAllisPresentTrueAndAnswerFalse();
        if (!students.isEmpty()) {
            Student student = RandomHelper.randomId(students);
            student.setL_isAnswer(true);
            studentDAO.save(student);
            allStudents.add(student);
        }
        return allStudents;
    }

    @Override
    public void changeMark(int id, double f_mark, double g_ansMark, double k_qMark) {
        Student student = studentDAO.getById(id);
        student.setF_mark(student.getF_mark() + f_mark);
        student.setG_ansMark(student.getG_ansMark() + g_ansMark);
        student.setK_qMark(student.getK_qMark() + k_qMark);
        studentDAO.save(student);
    }

    @Override
    public List<Student> getAllLateStudents() {
        return studentDAO.findAllisNotPresent();
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> optionalStudent = studentDAO.findById(id);
        return optionalStudent.get();
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    @Override
    public List<Student> getRandomStudents() {
        return studentDAO.findAllisPresentTrueAndAnswerTrue();
    }


}
