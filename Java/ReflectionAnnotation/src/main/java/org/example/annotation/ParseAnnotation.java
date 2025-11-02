package org.example.annotation;

import java.io.File;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 解析注解
 */
public class ParseAnnotation {
    public static void main(String[] args) throws Exception {
        //get class object by reflection
        Class<App> c = App.class;

        //parse annotation
        if (c.isAnnotationPresent(SimpleAnnotation.class)) {
            //Annotation simple = c.getDeclaredAnnotation(SimpleAnnotation.class);
            SimpleAnnotation simple = c.getDeclaredAnnotation(SimpleAnnotation.class);
            System.out.println(simple.name() + " " + simple.age());
        }

        //parse annotation
        Method m = c.getDeclaredMethod("fun1");
        if (m.isAnnotationPresent(SimpleAnnotation.class)) {
            SimpleAnnotation simple = m.getDeclaredAnnotation(SimpleAnnotation.class);
            System.out.println(simple.name() + " " + simple.age());
        }

        //parse annotation
        Field f = c.getDeclaredField("hello");
        if (f.isAnnotationPresent(ValueAnnotation.class)) {
            ValueAnnotation value = f.getDeclaredAnnotation(ValueAnnotation.class);
            System.out.println(value.value());
        }

    }
}
