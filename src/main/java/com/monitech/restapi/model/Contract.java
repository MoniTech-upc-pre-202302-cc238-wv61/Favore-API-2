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
@Table(name="Contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_id;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime publishDate;

    @Column(nullable = false, length = 255)
    private String status;

    @Column(name = "payment_method",nullable = false, length = 255)
    private String paymentMethod;

    @Column(nullable = false)
    private Double ammount;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}