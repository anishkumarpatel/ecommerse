package com.example.ecommerse.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class will ha
// ve multiple methods
// that will be serving HTTP Requests at /hello
// This class will be service Rest (HTTP) APIS
// localhost:8080/hello
@RestController
@RequestMapping("/hello")
public class HelloController {

    // GET /hello/say
    @GetMapping("/say/{name}/{times}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("times") int times) {
        String answer = "";

        for (int i = 0; i < times; ++i) {
            answer += " Hello "+(i+1)+" " + name;
            answer += " <br>";
        }

        return answer;
    }
    // Something in curly braces becomes a variables
}
