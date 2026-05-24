package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true, length = 40)
    private String username;

    @Column(name = "full_name", nullable = false, length = 80)
    private String fullName;

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String role = "OPERARIO"; //ADMIN | OPERARIO | VENDEDOR

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive;

    @Column(name = "create_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
