package org.example.reflection;

/**
 * 通过反射获取类对象的三种方式
 */
public class GetClassObject {
    public static void main(String[] args) throws Exception {
        //first way
        Class<Student> s1 = Student.class;

        //second way(full class name)
        Class s2 = Class.forName("org.example.reflection.Student");

        //third way
        Student student = new Student();
        Class<Student> s3 = (Class<Student>) student.getClass();

        //all class object equals
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);

        System.out.println(s1.getName()); // full class name
        System.out.println(s1.getSimpleName()); // class name

    }
}
