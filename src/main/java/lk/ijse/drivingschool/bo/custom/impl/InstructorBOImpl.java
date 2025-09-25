package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.InstructorBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.InstructorDAO;
import lk.ijse.drivingschool.dto.InstructorDTO;
import lk.ijse.drivingschool.entity.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {
    InstructorDAO instructorDAO =(InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    @Override
    public List<InstructorDTO> getAllInstructor() {
        List<Instructor> entity = instructorDAO.getAll();
        List<InstructorDTO> instructorDTO = new ArrayList<>();
        for (Instructor s : entity) {
            instructorDTO.add(new InstructorDTO(
                    s.getInstructorId(),
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getAddress(),
                    s.getDateOfBirth(),
                    s.getSpecialization(),
                    s.getExperienceYears()
            ));
        }
        return instructorDTO;
    }

    @Override
    public boolean deleteInstructor(String instructorId) {
        return instructorDAO.delete(instructorId);
    }

    @Override
    public boolean updateInstructor(InstructorDTO instructorDTO) {
        return instructorDAO.update(EntityDTOConverter.toInstructorEntity(instructorDTO));
    }

    @Override
    public InstructorDTO getOneInstructor(String instructorId) {
        return EntityDTOConverter.toInstructorDTO(instructorDAO.getOne(instructorId));
    }

    @Override
    public boolean saveInstructor(InstructorDTO instructorDTO) {
        return instructorDAO.save(EntityDTOConverter.toInstructorEntity(instructorDTO));
    }



}
