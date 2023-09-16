package com.monitech.restapi.repository;

import com.monitech.restapi.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
        
        boolean existsByApplicationUserId(Long id);
    
        Application findByApplicationUserId(Long id);
    
}
