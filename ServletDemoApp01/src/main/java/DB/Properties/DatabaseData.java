package DB.Properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



@Data
@AllArgsConstructor
@Builder
public class DatabaseData {
    private final String URL="jdbc:mysql://localhost:3306/CourseManagementApp";
    private  final String user="root";
    private  final String password="vishwanj@2316";
}
