package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.service.EmployeeControllerService;
import co.norse.hr.mainservice.service.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private static EmployeeControllerService employeeControllerService;
    @Autowired
    private EmployeeRepository employeeRepository;

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
    public static String newEmployee(@RequestParam String name, @RequestParam int companyId, @RequestParam String lastName,
                                     @RequestParam int birthday, @RequestParam String email, @RequestParam String phone,
                                     @RequestParam String room, @RequestParam String position, @RequestParam String info) {
        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setBirthday(birthday);
        employee.setCompanyId(companyId);
        employee.setDescription(info);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setRoomNumber(room);
        employee.setPhone(phone);
        employee.setPosition(position);
        employee.setOfficeId(1);
        employeeControllerService.saveEmployee(employee);
        return employee.toString();
    }
}
