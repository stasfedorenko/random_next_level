package com.random.service;

import com.random.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    List<Student> getAllLateStudents();

    Student getStudent(int id);

    void saveStudent(Student student);


    List<Student> getRandomStudents();

    List<Student> getAllStudents2();

    void changeMark(int id,double f_mark,double g_ansMark, double k_qMark);
}
