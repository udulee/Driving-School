package lk.ijse.drivingschool.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTM {
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


}

