package lk.ijse.drivingschool.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_date", nullable = false)
    private String paymentDate;

    @Column(name = "payment_method", length = 50)
    private String paymentMethod; // CASH, CARD, BANK_TRANSFER, CHEQUE

    @Column(name = "payment_status", length = 20)
    private String paymentStatus = "PENDING"; // PENDING, COMPLETED, FAILED, REFUNDED

    @Column(name = "reference_no", unique = true, length = 100)
    private String referenceNo;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "created_date")
    private String createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;
}
