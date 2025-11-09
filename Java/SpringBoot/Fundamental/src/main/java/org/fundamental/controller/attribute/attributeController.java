package org.fundamental.controller.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置文件读取
 */

@RestController
@RequestMapping("/attribute")
public class attributeController {


    @Value("${admin.name}")
    private String name;

    @Value("${admin.subjects[1]}")
    private String subject;

    @Autowired
    private Environment attributes;

    @Autowired
    private Admin admin;

    @GetMapping
    public String getName() {
        System.out.println(attributes.getProperty("admin.name"));
        System.out.println(attributes.getProperty("admin.subjects[2]"));
        System.out.println(admin);
        return name + " " + subject;
    }
}
