package lk.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // ,eken auto generate wenwa athin id ywanna one na
    private long studentId;
    private String name;
    private String email;
    private String phone;
    private String address;

    @Column(name = "registration_date")
    private String registrationDate;
}
