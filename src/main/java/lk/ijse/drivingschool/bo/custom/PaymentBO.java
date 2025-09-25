package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.bo.SuperBO;
import lk.ijse.drivingschool.dto.PaymentDTO;
import lk.ijse.drivingschool.entity.Payment;

import java.util.List;

public interface PaymentBO extends SuperBO {
    List<PaymentDTO> getAllPayment() ;
    boolean deletePayment(String id) ;
    boolean updatePayment(PaymentDTO paymentDTO) ;
    PaymentDTO getOnePayment(String id);
    boolean savePayment(PaymentDTO paymentDTO) ;
}
