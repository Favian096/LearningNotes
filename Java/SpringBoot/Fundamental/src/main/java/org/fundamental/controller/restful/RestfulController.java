package org.fundamental.controller.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 基础的RESTful
 */

@Controller
public class RestfulController {

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@PathVariable Integer id) {
        System.out.println("RESTful getUser... ");
        return "RESTful getUser... ";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestBody String user) {
        System.out.println("RESTful saveUser... ");
        return "RESTful saveUser... ";
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    @ResponseBody
    public String updateUser(@RequestBody String user) {
        System.out.println("RESTful putUser... ");
        return "RESTful putUser... ";
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@PathVariable Integer id) {
        System.out.println("RESTful deleteUser... ");
        return "RESTful deleteUser... ";
    }

}
