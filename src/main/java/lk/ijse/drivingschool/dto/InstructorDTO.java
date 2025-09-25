package lk.ijse.drivingschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InstructorDTO {
    private String instructorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private String specialization;
    private int experienceYears;

    public InstructorDTO(String firstName, String lastName, String email, String phone,
                         String address, String dateOfBirth, String specialization, int experienceYears) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
    }
}
