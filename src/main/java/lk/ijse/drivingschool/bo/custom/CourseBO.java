package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.bo.SuperBO;
import lk.ijse.drivingschool.config.FactoryConfiguration;
import lk.ijse.drivingschool.dto.CourseDTO;
import lk.ijse.drivingschool.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public interface CourseBO extends SuperBO {

      List<CourseDTO> getAllCourse() ;
      boolean saveCourse(CourseDTO courseDTO) ;
      boolean deleteCourse(String id) ;
      boolean updateCourse(CourseDTO courseDTO) ;
      CourseDTO getOneCourse(String id) ;


}
