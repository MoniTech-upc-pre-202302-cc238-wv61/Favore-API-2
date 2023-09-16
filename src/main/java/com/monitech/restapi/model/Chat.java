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
@Table(name="Chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_id;

    @ManyToOne
    @JoinColumn(name = "usuario1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "usuario2_id", nullable = false)
    private User user2;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
}
