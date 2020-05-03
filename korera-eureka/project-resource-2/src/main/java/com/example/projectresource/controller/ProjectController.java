package com.example.projectresource.controller;


import com.example.projectresource.dbModels.*;

import com.example.projectresource.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @GetMapping("/read/{projectId}")
    public Project read(@PathVariable Integer projectId) {

        Project project = projectService.get(projectId);
        return project;
    }


}
