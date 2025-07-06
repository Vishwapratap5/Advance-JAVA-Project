package Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class StudentManagement {
    private int studentID;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;

}
