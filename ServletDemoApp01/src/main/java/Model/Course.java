package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {
    private int courseID;
    private String courseName;
    private int courseFees;
    private String courseDescription;
    private String courseDuration;
}
