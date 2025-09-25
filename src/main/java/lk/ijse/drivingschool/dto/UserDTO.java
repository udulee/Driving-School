package lk.ijse.drivingschool.dto;

import lk.ijse.drivingschool.entity.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userId;
    private String username;
    private String password;
    private String email;
    private UserRole role; // "ADMIN" or "RECEPTIONIST"
    private String status; // "ACTIVE" or "INACTIVE"
    private String createdDate;
    private String lastLoginDate;

    public UserDTO(String username, String password, String email, UserRole role, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
    }
}

