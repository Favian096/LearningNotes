package com.example.Controller;

import com.example.Controller.utils.R;
import com.example.Obj.Student;
import com.example.serive.StudentServiceFromMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentServiceFromMP studentServiceFromMP;

    @GetMapping("/{id}")
    public R getById(@PathVariable int id) {
        return new R(true, studentServiceFromMP.getById(id));
    }

    @GetMapping
    public R getAll() {
        return new R(true, studentServiceFromMP.list());
    }

    @PostMapping
    public R save(@RequestBody Student student) {
        return new R(studentServiceFromMP.save(student));
    }

    @PutMapping
    public R update(@RequestBody Student student) {
        return new R(studentServiceFromMP.updateById(student));
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable int id) {
        return new R(studentServiceFromMP.removeById(id));
    }

    @GetMapping("/{current}/{pageSize}")
    public R getPage(@PathVariable int current, @PathVariable int pageSize) {
        return new R(true, studentServiceFromMP.getPage(current, pageSize));
    }
}
