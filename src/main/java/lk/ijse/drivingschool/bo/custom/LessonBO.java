package lk.ijse.drivingschool.bo.custom;

import lk.ijse.drivingschool.bo.SuperBO;
import lk.ijse.drivingschool.dto.LessonDTO;
import lk.ijse.drivingschool.entity.Lesson;

import java.util.List;

public interface LessonBO extends SuperBO {
    List<LessonDTO> getAllLesson();
    boolean saveLesson(LessonDTO lessonDTO);
    boolean deletelesson(String lessonId);
    boolean updateLesson(LessonDTO lessonDTO);
    LessonDTO getOneLesson(String lessonId);
}
