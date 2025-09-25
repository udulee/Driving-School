package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.StudentBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.StudentDAO;
import lk.ijse.drivingschool.entity.Student;
import lk.ijse.drivingschool.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> getAllStudent() {
        List<Student> entity = studentDAO.getAll();
        List<StudentDTO> studentDTO = new ArrayList<>();
        for (Student s : entity) {
            studentDTO.add(new StudentDTO(
                    s.getStudentId(),
                    s.getName(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getAddress(),
                    s.getRegistrationDate()));
        }
        return studentDTO;
    }

    @Override
    public boolean deleteStudent(String id)  {
        return studentDAO.delete(id);
    }
    @Override
    public boolean updateStudent(StudentDTO studentDTO){
        return studentDAO.update(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getStudentEmail(),
                studentDTO.getStudentPhone(),
                studentDTO.getStudentAddress(),
                studentDTO.getRegisterDate()));
    }
    @Override
    public StudentDTO getOneStudent(String id) {
        return EntityDTOConverter.toStudentDTO(studentDAO.getOne(id));
    }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) {
        return studentDAO.save(new Student(
                studentDTO.getStudentID(),
                studentDTO.getName(),
                studentDTO.getStudentEmail(),
                studentDTO.getStudentPhone(),
                studentDTO.getStudentAddress(),
                studentDTO.getRegisterDate()));
    }
}