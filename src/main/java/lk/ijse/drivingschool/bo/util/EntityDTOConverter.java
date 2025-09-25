package lk.ijse.drivingschool.bo.util;

import lk.ijse.drivingschool.dto.*;
import lk.ijse.drivingschool.entity.*;

public class EntityDTOConverter {

    // ---------- Course ----------
    public static CourseDTO toCourseDTO(Course course) {
        if (course == null) return null;
        return new CourseDTO(
                course.getCourseName(),
                course.getDuration(),
                course.getFee(),
                course.getStatus(),
                course.getDescription()
        );
    }

    public static Course toCourseEntity(CourseDTO dto) {
        if (dto == null) return null;
        Course course = new Course();
        course.setCourseName(dto.getCourseName());
        course.setDuration(dto.getDuration());
        course.setFee(dto.getFee());
        course.setStatus(dto.getStatus());
        course.setDescription(dto.getDescription());
        return course;
    }

    // ---------- Instructor ----------
    public static InstructorDTO toInstructorDTO(Instructor instructor) {
        if (instructor == null) return null;
        return new InstructorDTO(
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getEmail(),
                instructor.getPhone(),
                instructor.getAddress(),
                instructor.getDateOfBirth(),
                instructor.getSpecialization(),
                instructor.getExperienceYears()
        );
    }

    public static Instructor toInstructorEntity(InstructorDTO dto) {
        if (dto == null) return null;
        Instructor instructor = new Instructor();
        instructor.setFirstName(dto.getFirstName());
        instructor.setLastName(dto.getLastName());
        instructor.setEmail(dto.getEmail());
        instructor.setPhone(dto.getPhone());
        instructor.setAddress(dto.getAddress());
        instructor.setDateOfBirth(dto.getDateOfBirth());
        instructor.setSpecialization(dto.getSpecialization());
        instructor.setExperienceYears(dto.getExperienceYears());
        return instructor;
    }

    // ---------- Lesson ----------
    public static LessonDTO toLessonDTO(Lesson lesson) {
        if (lesson == null) return null;
        return new LessonDTO(
                lesson.getDate(),
                lesson.getTime(),
                lesson.getStatus(),
                lesson.getStudent() != null ? String.valueOf(lesson.getStudent().getStudentId()) : null,
                lesson.getCourse() != null ? String.valueOf(lesson.getCourse().getCourseId()) : null,
                lesson.getInstructor() != null ? String.valueOf(lesson.getInstructor().getInstructorId()) : null
        );
    }

    public static Lesson toLessonEntity(LessonDTO dto) {
        if (dto == null) return null;
        Lesson lesson = new Lesson();
        lesson.setDate(dto.getDate());
        lesson.setTime(dto.getTime());
        lesson.setStatus(dto.getStatus());
//        lesson.setStudent(student);
//        lesson.setCourse(course);
//        lesson.setInstructor(instructor);
        return lesson;
    }

    // ---------- Payment ----------
    public static PaymentDTO toPaymentDTO(Payment payment) {
        if (payment == null) return null;
        return new PaymentDTO(
                payment.getStudent() != null ? String.valueOf(payment.getStudent().getStudentId()) : null,
                payment.getCourse() != null ? String.valueOf(payment.getCourse().getCourseId()) : null,
                payment.getAmount(),
                payment.getPaymentDate(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getReferenceNo(),
                payment.getNotes()
        );
    }

    public static Payment toPaymentEntity(PaymentDTO dto, Student student, Course course) {
        if (dto == null) return null;
        Payment payment = new Payment();
        payment.setStudent(student);
        payment.setCourse(course);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setReferenceNo(dto.getReferenceNo());
        payment.setNotes(dto.getNotes());
        return payment;
    }

    // ---------- Student ----------
    public static StudentDTO toStudentDTO(Student student) {
        if (student == null) return null;
        return new StudentDTO(
                student.getStudentId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                student.getAddress(),
                student.getRegistrationDate()
        );
    }

    public static Student toStudentEntity(StudentDTO dto) {
        if (dto == null) return null;
        Student student = new Student();
        student.setStudentId(dto.getStudentID());
        student.setName(dto.getName());
        student.setEmail(dto.getStudentEmail());
        student.setPhone(dto.getStudentPhone());
        student.setAddress(dto.getStudentAddress());
        student.setRegistrationDate(dto.getRegisterDate());
        return student;
    }

    // ---------- User ----------
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }

    public static User toUserEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        return user;
    }
}
