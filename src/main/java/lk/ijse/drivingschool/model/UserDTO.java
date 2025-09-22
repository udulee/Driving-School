package lk.ijse.drivingschool.model;

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
    private String firstName;
    private String lastName;
    private String role; // "ADMIN" or "RECEPTIONIST"
    private String status; // "ACTIVE" or "INACTIVE"
    private String createdDate;
    private String lastLoginDate;

    public UserDTO(String username, String password, String email, String firstName,
                   String lastName, String role, String status) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getRoleDescription() {
        return role.substring(0, 1) + role.substring(1).toLowerCase();
    }
}

