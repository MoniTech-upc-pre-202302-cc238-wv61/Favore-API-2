package com.monitech.restapi.repository;

import com.monitech.restapi.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Application a WHERE a.user.id = :userId")
    boolean existsByApplicationUserId(@Param("userId") Long userId);
    
    @Query("SELECT a FROM Application a WHERE a.user.id = :userId")
    Application findByApplicationUserId(@Param("userId") Long userId);
    
}
