package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.bo.SuperBO;
import lk.ijse.drivingschool.entity.Student;
import lk.ijse.drivingschool.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
     List<StudentDTO> getAllStudent() ;
    boolean deleteStudent(String id) ;
    boolean updateStudent(StudentDTO studentDTO) ;
    StudentDTO getOneStudent(String id);
    boolean saveStudent(StudentDTO studentDTO) ;
}
