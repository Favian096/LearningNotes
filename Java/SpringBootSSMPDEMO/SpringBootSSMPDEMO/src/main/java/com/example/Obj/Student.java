package com.example.Obj;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//由于引入了lombok, 直接使用@data即可实现所有的getter和getter和toString()等方法
@Data
//为解决实体类与实际表名不同, 可在此处命名表名
@TableName("students")
public class Student {
    private int id;
    private int classId;
    private String name;
    private char gender;
    private int score;
}
