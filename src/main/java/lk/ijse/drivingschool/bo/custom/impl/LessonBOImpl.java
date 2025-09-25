package lk.ijse.drivingschool.bo.custom.impl;

import lk.ijse.drivingschool.bo.custom.LessonBO;
import lk.ijse.drivingschool.bo.util.EntityDTOConverter;
import lk.ijse.drivingschool.dao.DAOFactory;
import lk.ijse.drivingschool.dao.custom.LessonDAO;
import lk.ijse.drivingschool.dto.LessonDTO;
import lk.ijse.drivingschool.entity.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonBOImpl implements LessonBO {
    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);

    @Override
    public List<LessonDTO> getAllLesson() {
        List<Lesson> entity = lessonDAO.getAll();
        List<LessonDTO> lessonDTO = new ArrayList<>();
        for (Lesson s : entity) {
            lessonDTO.add(EntityDTOConverter.toLessonDTO(s));
        }
        return lessonDTO;
    }

    @Override
    public boolean saveLesson(LessonDTO lessonDTO) {
        return lessonDAO.save(EntityDTOConverter.toLessonEntity(lessonDTO));
    }

    @Override
    public boolean deletelesson(String lessonId) {
        return lessonDAO.delete(lessonId);
    }


    @Override
    public boolean updateLesson(LessonDTO lessonDTO) {
        return lessonDAO.update(EntityDTOConverter.toLessonEntity(lessonDTO));
    }

    @Override
    public LessonDTO getOneLesson(String lessonId) {
        return EntityDTOConverter.toLessonDTO(lessonDAO.getOne(lessonId));
    }
}
