package lk.ijse.drivingschool.dao;

import lk.ijse.drivingschool.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory==null)?new DAOFactory():daoFactory;
    }
    public enum DAOTypes {
        STUDENT,
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT ,
        USER
    }
    public SuperDao getDAO(DAOTypes daoType) {
        switch(daoType){
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}