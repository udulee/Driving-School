package lk.ijse.drivingschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class  LessonDTO {
    private String lessonId;
    private String date;
    private String time;
    private String status;
    private String studentId;
    private String courseId;
    private String instructorId;

    public LessonDTO(String date, String time, String status, String studentId,
                     String courseId, String instructorId) {
        this.date = date;
        this.time = time;
        this.status = status;
        this.studentId = studentId;
        this.courseId = courseId;
        this.instructorId = instructorId;
    }
    public String getDateTime() {
        return date + " " + time;
    }
}
