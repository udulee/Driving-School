package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.CourseBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.CourseDAO;
import lk.ijse.drivingschool.dto.CourseDTO;
import lk.ijse.drivingschool.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);


    @Override
    public List<CourseDTO> getAllCourse() {
        List<Course> entity = courseDAO.getAll();
        List<CourseDTO> courseDTOS = new ArrayList<CourseDTO>();
        for (Course c : entity) {
            CourseDTO courseDTO = new CourseDTO(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getDuration(),
                    c.getFee(),
                    c.getStatus(),
                    c.getDescription());
        }
        return courseDTOS;
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) {
        return courseDAO.save(EntityDTOConverter.toCourseEntity(courseDTO));
    }

    @Override
    public boolean deleteCourse(String id) {
        return courseDAO.delete(id);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.update(EntityDTOConverter.toCourseEntity(courseDTO));
    }

    @Override
    public CourseDTO getOneCourse(String id) {
        return EntityDTOConverter.toCourseDTO(courseDAO.getOne(id));
    }
}
