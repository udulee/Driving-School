package lk.ijse.drivingschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private String courseId;
    private String courseName;
    private String duration;
    private double fee;
    private String status;
    private String description;

    public CourseDTO(String courseName, String duration, double fee, String status, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.fee = fee;
        this.status = status;
        this.description = description;
    }
}