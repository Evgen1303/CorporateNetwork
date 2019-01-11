package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
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
    public static String solve(@RequestParam double a, @RequestParam double b, @RequestParam double c) {
        double D = b * b - 4 * a * c;
        if (D < 0) {
            return "no answer";
        } else if (D == 0) {
            double x = -1 * b / (2 * a);
            return "answer: " + x + "<br>a = " + a + " b = " + b + " c = " + c;
        } else {
            double x1 = (-1 * b - Math.sqrt(D)) / (2 * a);
            double x2 = (-1 * b + Math.sqrt(D)) / (2 * a);
            return "answer: x1 = " + x1 + "x2 = " + x2;
        }
    }

    @GetMapping("employee")
    public static String newEmployee(@RequestParam String name) {
        Employee employee = new Employee();
        employee.setFirstName(name);
        return employee.toString();
    }
}
