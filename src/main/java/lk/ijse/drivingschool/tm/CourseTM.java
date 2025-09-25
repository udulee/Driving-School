package lk.ijse.drivingschool.tm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseTM {
    private String courseId;
    private String courseName;
    private String duration;
    private double free;
    private String status;
    private String description;

}