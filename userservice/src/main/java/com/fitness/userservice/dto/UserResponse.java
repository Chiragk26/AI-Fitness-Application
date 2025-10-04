package com.fitness.userservice.dto;

import com.fitness.userservice.model.UserRole;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String keycloakId;
    private String password;
    private String firstname;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
