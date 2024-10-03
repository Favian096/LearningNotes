package com.example.Controller;

import com.example.Obj.Student;
import com.example.serive.StudentServiceFromMP;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceFromMpTests {
    @Autowired
    StudentServiceFromMP studentServiceFromMP;

    @Test
    void insertTest() {
        Student student = new Student();
        student.setName("Du_du");
        student.setClassId(1);
        student.setGender('W');
        student.setScore(100);
        studentServiceFromMP.save(student);
    }

    @Test
    void deleteByIdTest() {
        studentServiceFromMP.removeById(137);
    }

    @Test
    void selectByIdTest() {
        System.out.println(studentServiceFromMP.getById(134)
        );
    }
}
