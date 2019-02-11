package com.spring.core.controllers;

import com.spring.core.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(path = "/modelandview")
    public ModelAndView greet(){
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("students", this.getStudents());
        return modelAndView;
    }

    @GetMapping(path="/path")
    public ModelAndView testParameters(@RequestParam(required = false, defaultValue = "Juan") String name){
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("student", new Student(name, "123"));
        return modelAndView;
    }

    @GetMapping(path="/route/{name}")
    public ModelAndView testParametersWithRoute(@PathVariable(name = "name") String name){
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("student", new Student(name, "123"));
        return modelAndView;
    }

}
