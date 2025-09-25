package lk.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255) // BCrypt encrypted
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    private UserRole role; // ADMIN, RECEPTIONIST

    @Column(name = "status", length = 20)
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, LOCKED

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "last_login_date")
    private String lastLoginDate;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts = 0;
}