package com.monitech.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monitech.restapi.exception.ValidationException;
import com.monitech.restapi.model.Application;
import com.monitech.restapi.service.ApplicationService;

import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("/api/favore/v1")
public class ApplicationController {
    
    private final ApplicationService applicationService;

    private void validateApplication(Application application) {
        if (application.getMessage() == null || application.getMessage().isEmpty()) {
            throw new ValidationException("Message is required");
        }
        if (application.getMessage().length() > 255) {
            throw new ValidationException("Message must not exceed 255 characters");
        }
        if (application.getDetails() == null || application.getDetails().isEmpty()) {
            throw new ValidationException("Details is required");
        }
        if (application.getDetails().length() > 255) {
            throw new ValidationException("Details must not exceed 255 characters");
        }
    }

    private void validateApplicationExistence(Application application) {
        if (applicationService.existsByApplicationUserId(application.getUser().getId())) {
            throw new ValidationException("Application with user id: " + application.getUser().getId() + " already exists");
        }
    }

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //URL: http://localhost:8080/api/favore/v1/applications
    //Method: POST
    @Transactional
    @PostMapping("/applications")
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        validateApplication(application);
        validateApplicationExistence(application);

        Application createdApplication = applicationService.createApplication(application);
        return ResponseEntity.ok(createdApplication);
    }

    //URL: http://localhost:8080/api/favore/v1/applications
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return ResponseEntity.ok(applications);
    }

    //URL: http://localhost:8080/api/favore/v1/applications/{id}
    //Method: GET
    @Transactional
    @GetMapping("/applications/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable Long id) {
        Application application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }

    //URL: http://localhost:8080/api/favore/v1/applications/{id}
    //Method: PUT
    @Transactional
    @PutMapping("/applications/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id, @RequestBody Application application) {
        validateApplication(application);
        Application updatedApplication = applicationService.updateApplication(id, application);
        return ResponseEntity.ok(updatedApplication);
    }

    //URL: http://localhost:8080/api/favore/v1/applications/{id}
    //Method: DELETE
    @Transactional
    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Application> deleteApplication(@PathVariable Long id) {
        Application deletedApplication = applicationService.deleteApplication(id);
        return ResponseEntity.ok(deletedApplication);
    }
}
