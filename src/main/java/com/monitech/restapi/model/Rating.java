package com.monitech.restapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rating_id;

    @ManyToOne
    @JoinColumn(name = "from_usuario_id", nullable = false)
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "for_usuario_id", nullable = false)
    private User forUser;

    @Column(nullable = false, length = 255)
    private String contract_id;

    @Column(nullable = false, length = 255)
    private String rating;

    @Column(nullable = false, length = 255)
    private String comment;
}