package ru.semyak.ScrtTrain.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.semyak.ScrtTrain.models.Application;
import ru.semyak.ScrtTrain.services.AppService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/apps")
public class AppController {

    private final AppService appService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Добро пожаловать на эту открытую страницу";
    }

    @GetMapping("/all-app")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<Application> allApplications() {
        return appService.allApplications();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Application getAppById(@PathVariable int id) {
        return appService.applicationById(id);
    }

}
