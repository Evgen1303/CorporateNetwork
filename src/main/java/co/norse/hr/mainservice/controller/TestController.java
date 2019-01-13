package co.norse.hr.mainservice.controller;

import co.norse.hr.mainservice.entity.Employee;
import co.norse.hr.mainservice.entity.Project;
import co.norse.hr.mainservice.entity.EmployeeProject;
import co.norse.hr.mainservice.repos.EmployeeProjectRepo;
import co.norse.hr.mainservice.repos.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("test")
public class TestController {


    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private EmployeeProjectRepo employeeProjectRepo;

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

    @PostMapping("project")
    public @ResponseBody String AddNewProject (@RequestParam String name, @RequestParam String description ){
        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        projectRepo.save(project);
        return "201 Saved\n\n"
                + "ID = " +project.getId()
                + "\n NAME = "+project.getName()
                +"\n DESCRIPTION = "+project.getDescription();
            }

    @GetMapping("projects")
    public @ResponseBody Iterable<Project> getAllProject(){
        return projectRepo.findAll();
    }
    @GetMapping("projects/{id}")
    public @ResponseBody Iterable<Project> getIdProject(@PathVariable int id){
        return projectRepo.findById(id);
    }
    @DeleteMapping("projects/{id}")
    public @ResponseBody String deleteProject(@PathVariable int id){
        projectRepo.deleteById(id);
        return "204 Deleted";
    }

    @PostMapping("employeeprojects")
    public @ResponseBody String AddNewEmployeeProject (@RequestParam int employee_id, @RequestParam int project_id, @RequestParam String position, @RequestParam int start, @RequestParam int end ){
    EmployeeProject employeeperoject = new EmployeeProject();
        employeeperoject.setEmployee_id(employee_id);
        employeeperoject.setProject_id(project_id);
        employeeperoject.setPosition(position);
        employeeperoject.setStart(start);
        employeeperoject.setEnd(end);
        employeeProjectRepo.save(employeeperoject);
        return "201 Saved\n\n"
                + "ID = " +employeeperoject.getId()
                + "\n EmployeeID = "+employeeperoject.getEmployee_id()
                + "\n ProjectID = "+employeeperoject.getProject_id()
                + "\n Position = "+employeeperoject.getPosition()
                + "\n Start = "+employeeperoject.getStart()
                + "\n End = "+employeeperoject.getEnd();

            }
    @GetMapping("employeeprojects")
    public @ResponseBody Iterable<EmployeeProject> getAllEmployeeProject(){
        return employeeProjectRepo.findAll();
    }
    @GetMapping("employeeprojects/{id}")
    public @ResponseBody Iterable<EmployeeProject> getIdEmployeeProject(@PathVariable int id){
        return employeeProjectRepo.findById(id);
    }
    @DeleteMapping("employeeprojects/{id}")
    public @ResponseBody String EmployeeProject(@PathVariable int id){
        employeeProjectRepo.deleteById(id);
        return "204 Deleted";
    }




}
