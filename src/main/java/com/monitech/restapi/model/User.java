package com.monitech.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.monitech.restapi.enums.UserType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false, unique = true)
    private String name;

    @Column(name="last_name", nullable = false, unique = true)
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false, unique = true)
    private String password;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name="image_url", nullable = false)
    private String imageUrl;

    @Column(name="active", nullable = false)
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private UserType type;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
