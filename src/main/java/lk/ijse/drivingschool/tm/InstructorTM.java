package lk.ijse.drivingschool.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorTM {
    private String instructorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String specialization;
    private int experienceYears;


}
