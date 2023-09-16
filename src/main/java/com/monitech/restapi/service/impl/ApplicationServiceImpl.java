package com.monitech.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitech.restapi.model.Application;
import com.monitech.restapi.repository.ApplicationRepository;
import com.monitech.restapi.service.ApplicationService;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    @Override
    public Application updateApplication(Long id, Application application) {
        Application applicationToUpdate = applicationRepository.findById(id).orElse(null);
        if (applicationToUpdate != null) {
            applicationToUpdate.setMessage(application.getMessage());
            applicationToUpdate.setDetails(application.getDetails());
            return applicationRepository.save(applicationToUpdate);
        }
        return null;
    }

    @Override
    public Application deleteApplication(Long id) {
        Application applicationToDelete = applicationRepository.findById(id).orElse(null);
        if (applicationToDelete != null) {
            applicationRepository.delete(applicationToDelete);
            return applicationToDelete;
        }
        return null;
    }

    @Override
    public boolean existsByApplicationUserId(Long id) {
        return applicationRepository.existsByApplicationUserId(id);
    }

    @Override
    public Application findByApplicationUserId(Long id) {
        return applicationRepository.findByApplicationUserId(id);
    }

}
