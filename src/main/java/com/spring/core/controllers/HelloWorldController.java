package com.spring.core.controllers;

import com.spring.core.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.TreeMap;


@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    public Map<String, Student> getStudents(){
        Map<String, Student> students = new TreeMap<>(); //Sorted Map simulating database
        students.put("123456789ID", new Student("Juan", "123456798ID"));
        return students;
    }

    @GetMapping(path="/world")
    public String greet(Model model){
        model.addAttribute("students", this.getStudents());
        return "welcome";
    }

}
