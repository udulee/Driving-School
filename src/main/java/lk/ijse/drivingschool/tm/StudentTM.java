package lk.ijse.drivingschool.tm;
 import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentTM {
    private  String name;
    private long studentID;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String NIC;
    private String registerDate;


}

