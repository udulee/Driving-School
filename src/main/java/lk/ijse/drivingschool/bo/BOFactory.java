package lk.ijse.drivingschool.bo;


import lk.ijse.drivingschool.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }
    public enum BOTypes{
        STUDENT,
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        USER
    }
    public SuperBO getBO(BOTypes boType) {
        switch(boType){
            case STUDENT:
                return new StudentBOImpl();
           case COURSE:
               return new CourseBOImpl();
            case INSTRUCTOR:
               return new InstructorBOImpl();
            case LESSON:
                return new LessonBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
           case USER:
               return new UserBOImpl();
            default:
                return null;
        }
    }
}