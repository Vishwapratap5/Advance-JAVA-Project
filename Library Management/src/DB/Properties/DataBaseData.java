package DB.Properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class DataBaseData {
    private final String URL="jdbc:mysql://127.0.0.1:3306/LibraryManagement";
    private final String USERNAME="root";
    private final String PASSWORD="vishwanj@2316";
}
