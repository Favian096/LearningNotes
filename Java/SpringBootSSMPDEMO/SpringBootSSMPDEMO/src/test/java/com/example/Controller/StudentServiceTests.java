package com.example.Controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Obj.Student;
import com.example.serive.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTests {
    @Autowired
    StudentService studentService;

    @Test
    void insertTest() {
        Student student = new Student();
        student.setName("Du_du");
        student.setClassId(1);
        student.setGender('W');
        student.setScore(100);
        studentService.insert(student);
    }

    @Test
    void updateByIdTest() {
        Student student = new Student();
        student.setId(99);
        student.setName("Test");
        student.setScore(55);
        student.setGender('W');
        student.setClassId(3);
        int i = studentService.updateById(student);
        System.out.println(i > 0 ? "True" : "False");
    }

    @Test
    void deleteByIdTest() {
        studentService.deleteById(135);
    }

    @Test
    void selectByIdTest() {
        System.out.println(studentService.selectById(134)
        );
    }

    @Test
    void selectListTest() {
        System.out.println(studentService.selectList(null));
    }

    @Test
    void selectPageTest() {
        IPage<Student> page = new Page();
        page = studentService.selectPage(2, 5);
        System.out.println(page.getRecords());
    }


}
