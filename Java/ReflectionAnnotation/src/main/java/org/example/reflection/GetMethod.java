package org.example.reflection;

import java.lang.reflect.Method;

/**
 * 通过反射获取类的成员方法
 */
public class GetMethod {
    public static void main(String[] args) throws Exception {
        //get class object by reflection
        Class<Student> s = Student.class;

        //get methods by reflection
        Method[] methods = s.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " + "
                    + method.getParameterCount() + " + "
                    + method.getReturnType());
        }
        System.out.println("==================");

        //get method by reflection
        Method setName = s.getMethod("setName", String.class);
        System.out.println(setName.getName() + " + "
                + setName.getParameterCount() + " + "
                + setName.getReturnType());

        Method sleep = s.getDeclaredMethod("sleep");
        System.out.println(sleep.getName() + " + "
                + sleep.getParameterCount() + " + "
                + sleep.getReturnType());

        //method invoke
        Student student = new Student();
        setName.invoke(student, "Aerith");

        sleep.setAccessible(true);
        String msg = (String) sleep.invoke(student);
        System.out.println(msg);
    }
}
