package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.dto.InstructorDTO;
import lk.ijse.drivingschool.entity.Instructor;

import java.util.List;

public interface InstructorBO extends StudentBO{
    List<InstructorDTO> getAllInstructor();
    boolean deleteInstructor(String instructorId);
    boolean updateInstructor(InstructorDTO instructorDTO);
    InstructorDTO getOneInstructor(String instructorId);
    boolean saveInstructor(InstructorDTO instructorDTO);

}
