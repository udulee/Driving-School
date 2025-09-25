package lk.ijse.drivingschool.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String name;
    private long studentID;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String NIC;
    private String registerDate;

    public StudentDTO(String name, String email, String phone, String address, String nic, String registerDate) {
        this.name = name;
        this.studentEmail = email;
        this.studentPhone = phone;
        this.studentAddress = address;
        this.NIC = nic;
        this.registerDate = registerDate;
    }

    public StudentDTO(long studentID, String name, String email, String phone, String address, String nic, String registerDate) {
        this.studentID = studentID;
        this.name = name;
        this.studentEmail = email;
        this.studentPhone = phone;
        this.studentAddress = address;
        this.NIC = nic;
        this.registerDate = registerDate;
    }

    public StudentDTO(long studentId, String name, String email, String phone, String address, String registrationDate) {
        this.studentID = studentId;
        this.name = name;
        this.studentEmail = email;
        this.studentPhone = phone;
        this.studentAddress = address;
        this.registerDate = registrationDate;

    }
}
