package com.monitech.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long application_id;

    @Column(nullable = false, length = 255)
    private String message;

    @Column(nullable = false, length = 255)
    private String details;

    @Column(name = "desired_compensation",nullable = false)
    private Double desiredCompensation;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}