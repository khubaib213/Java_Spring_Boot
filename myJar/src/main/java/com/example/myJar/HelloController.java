package com.example.myJar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class HelloController {
        @GetMapping("/hello")
        public String hello()
        {
            return "Hello from the Spring boot";
        }
        @GetMapping("/about")
        public String about()
        {
            return  "This is the about page";
        }
        @GetMapping("/name")
        public String name()
        {
            return "Developer";
        }

    }
