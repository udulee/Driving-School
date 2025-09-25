package lk.ijse.drivingschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentDTO {
    private String paymentId;
    private String studentId;
    private String courseId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String paymentStatus;
    private String referenceNo;
    private String notes;

    public PaymentDTO(String studentId, String courseId, double amount, String paymentDate,
                      String paymentMethod, String paymentStatus, String referenceNo, String notes) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.referenceNo = referenceNo;
        this.notes = notes;
    }
    public String getFormattedAmount() {
        return String.format("%.2f LKR", amount);
    }

    public String getPaymentSummary() {
        return paymentMethod + " - " + getFormattedAmount() + " (" + paymentStatus + ")";
    }
}
