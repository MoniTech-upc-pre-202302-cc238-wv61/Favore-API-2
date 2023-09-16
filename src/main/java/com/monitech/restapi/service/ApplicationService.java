package com.monitech.restapi.service;

import java.util.List;

import com.monitech.restapi.model.Application;


public interface ApplicationService {
    
    Application createApplication(Application application);

    Application updateApplication(Long application_id, Application application);

    Application getApplicationById(Long application_id);

    Application deleteApplication(Long application_id);

    List<Application> getAllApplications();
}
