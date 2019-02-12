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
@RequestMapping("/hello") //Parent route to access controller
public class HelloWorldController {

    public Map<String, Student> getStudents(){
        Map<String, Student> students = new TreeMap<>(); //Sorted Map simulating database
        students.put("123456789ID", new Student("Juan", "123456798ID"));
        return students;
    }

    /**
     * Hello world using the map created before
     * @param model model to be sent to the template
     * @return html template to be rendered
     */
    @GetMapping(path="/world")
    public String greet(Model model){
        model.addAttribute("students", this.getStudents());
        return "welcome";
    }

    /**
     *  Different approach to rendering templates, returning the View and the Model instead of the template's name
     * @return ModelAndView containing the template to be rendered and the model that it uses
     */
    @GetMapping(path = "/modelandview")
    public ModelAndView greet(){
        ModelAndView modelAndView = new ModelAndView("welcome"); //name of the template
        modelAndView.addObject("students", this.getStudents()); //model to be used in the template
        return modelAndView;
    }

    /**
     *  Method to send parameters in the url to the template
     * @param name parameter in the query
     * @return ModelandView containing the new Student and the name of the template
     */
    @GetMapping(path="/path")
    public ModelAndView testParameters(@RequestParam(required = false, defaultValue = "Juan") String name){
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("student", new Student(name, "123")); //gets name from query
        return modelAndView;
    }

    /**
     * Using routes instead of queries to send parameters
     * @param name name sent via route instead of query
     * @return ModelAndView similar to method used before but now gets the parameter from route
     */
    @GetMapping(path="/route/{name}") //Route to access method
    public ModelAndView testParametersWithRoute(@PathVariable(name = "name") String name){
        ModelAndView modelAndView = new ModelAndView("post");
        modelAndView.addObject("student", new Student(name, "123"));
        return modelAndView;
    }

}
