package org.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 获取反射对象的成员变量
 */
public class GetVariable {
    public static void main(String[] args) throws Exception {
        //create object
        Student student = new Student();
        student.setName("Tom");
        student.setAge(20);

        //get class object and variables by reflection
        Class<Student> s = (Class<Student>) student.getClass();
        Field[] fields = s.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName() + "-->" + field.getType());
        }

        //get variable value
        Field fName = s.getField("name");
        System.out.println((String) fName.get(student));

        Field fAge = s.getDeclaredField("age");
        fAge.setAccessible(true);
        System.out.println((int) fAge.get(student));

        // set variable value
        fName.set(student, "John");
        System.out.println(fName.get(student));

        fAge.set(student, 21);
        System.out.println(fAge.get(student));

        System.out.println(student);
    }
}
