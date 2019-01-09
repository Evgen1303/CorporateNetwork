package co.norse.hr.mainservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public List<String> getUsers() {
        List<String> userNames = Arrays.asList("qwe", "asd", "retert");

        return userNames;
    }
}
