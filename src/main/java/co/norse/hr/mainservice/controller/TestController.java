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
}
