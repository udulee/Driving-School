package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.PaymentBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.CourseDAO;
import lk.ijse.drivingschool.dao.custom.PaymentDAO;
import lk.ijse.drivingschool.dao.custom.StudentDAO;
import lk.ijse.drivingschool.dto.PaymentDTO;
import lk.ijse.drivingschool.entity.Course;
import lk.ijse.drivingschool.entity.Payment;
import lk.ijse.drivingschool.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public List<PaymentDTO> getAllPayment() {
        List<Payment> entity = paymentDAO.getAll();
        List<PaymentDTO> paymentDTO = new ArrayList<>();
        for (Payment s : entity) {
            paymentDTO.add(EntityDTOConverter.toPaymentDTO(s));
        }
        return paymentDTO;
    }

    @Override
    public boolean deletePayment(String id) {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) {
        Student student  = studentDAO.getOne(paymentDTO.getStudentId());
        Course course  = courseDAO.getOne(paymentDTO.getCourseId());
        return paymentDAO.update(EntityDTOConverter.toPaymentEntity(paymentDTO,student,course));
    }

    @Override
    public PaymentDTO getOnePayment(String id) {
        return EntityDTOConverter.toPaymentDTO(paymentDAO.getOne(id));
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) {
        Student student  = studentDAO.getOne(paymentDTO.getStudentId());
        Course course  = courseDAO.getOne(paymentDTO.getCourseId());
        return paymentDAO.save(EntityDTOConverter.toPaymentEntity(paymentDTO,student,course));
    }
}
