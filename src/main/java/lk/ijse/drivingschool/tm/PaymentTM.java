package lk.ijse.drivingschool.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentTM {
    private String paymentId;
    private String studentId;
    private String courseId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    private String referenceNo;
    private String notes;

}
