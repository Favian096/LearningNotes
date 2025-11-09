package org.fundamental.controller.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基础的RESTful 简化
 */

@RestController // => @RequestBody + @Controller
@RequestMapping("/simple")
public class RestfulSimpleController {

    @GetMapping(value = "/{id}")
    public String getUser(@PathVariable Integer id) {
        System.out.println("RESTful getUser... ");
        return "RESTful getUser... ";
    }

    @PostMapping
    public String saveUser(@RequestBody String user) {
        System.out.println("RESTful saveUser... ");
        return "RESTful saveUser... ";
    }

    @RequestMapping
    public String updateUser(@RequestBody String user) {
        System.out.println("RESTful putUser... ");
        return "RESTful putUser... ";
    }

    @RequestMapping(value = "/{id}")
    public String deleteUser(@PathVariable Integer id) {
        System.out.println("RESTful deleteUser... ");
        return "RESTful deleteUser... ";
    }

}
