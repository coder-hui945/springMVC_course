package com.wuminghui.service;

import com.wuminghui.domain.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);
    List<Student> findStudent();
}
