package org.example.reflection;

import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 通过获取构造器, 实例化对象
 */
public class GetConstructor {
    public static void main(String[] args) throws Exception {

        //get class object by reflection
        Class<Student> student = Student.class;

        //get public constructor
        Constructor constructor = student.getConstructor();
        Student s1 = (Student) constructor.newInstance();
        System.out.println(s1);

        //get private constructor
        Constructor constructor2 = student.getDeclaredConstructor(String.class, int.class);
        constructor2.setAccessible(true);
        Student s2 = (Student) constructor2.newInstance("John", 12);
        System.out.println(s2);

    }
}
