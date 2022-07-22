package hu.uni.miskolc.lev.java.CourseBoot.service;

import hu.uni.miskolc.lev.java.CourseBoot.model.entity.Student;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);
    void updateStudent(int student_id, String email);
    Boolean deleteStudent(int student_id);
     List<Student> getAllStudent();
    String lastAddedStudent(Student student);
}