package lk.ijse.drivingschool.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonTM {
    private String lessonId;
    private String date;
    private String time;
    private String status;
    private String studentId;
    private String courseId;
    private String instructorId;


}
