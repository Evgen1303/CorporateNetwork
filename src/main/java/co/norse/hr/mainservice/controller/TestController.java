package co.norse.hr.mainservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @GetMapping("name")
    public String hello2(@RequestParam String firstName, @RequestParam String lastName) {
        return "Hello " + firstName + " " + lastName;
    }

    @GetMapping("name/{firstName}")
    public String hello3(@PathVariable String firstName) {
        return "Hello " + firstName;
    }

    @GetMapping("equation")
    public String solve(@RequestParam double a, @RequestParam double b, @RequestParam double c){
        double D = b*b - 4*a*c;
        if (D<0) {
            return "no answer";
        } else if (D==0) {
            double x = (b - Math.sqrt(D))/(2*a);
            return "answer: " + x;
        } else {
            double x1 = (b - Math.sqrt(D))/(2*a);
            double x2 = (b + Math.sqrt(D))/(2*a);
            return "answer: x1 = " + x1 + "x2 = " + x2;
        }
    }
}
