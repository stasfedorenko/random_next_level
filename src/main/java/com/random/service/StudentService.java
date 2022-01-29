package com.random.service;

import com.random.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudents();

    List<Student> getAllLateStudents();

    Student getStudent(int id);

    void deleteStudent(int id);

    void saveStudent(Student student);

    void saveStudents(List<Student> student);

    int save();

    List<Student> getRandomStudents();

    int getRandomId();

    List<Student> getAllStudents2();
}
