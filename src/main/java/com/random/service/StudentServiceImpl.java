package com.random.service;


import com.random.DAO.StudentDAO;
import com.random.entity.Student;
import com.random.helperClass.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
        List<Student> studentList = ExcelHelper.excelToStudent();
        studentDAO.saveAll(studentList);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAllisPresentTrue();
    }

    @Override
    public List<Student> getAllStudents2() {
        return studentDAO.findAllisPresentTrueAndAnswerFalse();
    }

    @Override
    public List<Student> getAllLateStudents() {
        return studentDAO.findAllisPresentFalse();
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> optionalStudent = studentDAO.findById(id);
        return optionalStudent.get();
    }

    @Override
    public void deleteStudent(int id) {
        studentDAO.deleteById(id);
    }

    @Override
    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    @Override
    public void saveStudents(List<Student> student) {
        studentDAO.saveAll(student);
    }

    @Override
    public int save() {
        List<Student> list = studentDAO.findAllisPresentTrue();
        Random random = new Random();
        int id = random.ints(1, list.size()).findFirst().getAsInt();

        return id;
    }

    @Override
    public List<Student> getRandomStudents() {
        return studentDAO.findAllisPresentTrueAndAnswerTrue();
    }

    @Override
    public int getRandomId() {
        Random random = new Random();
        return random.ints(1, 9).findFirst().getAsInt();
    }

}
