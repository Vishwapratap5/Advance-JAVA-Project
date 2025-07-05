package DB.Properties;

import lombok.*;

@AllArgsConstructor
@Data
@ToString
public class DataBaseData {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/ExpenseTracker";
    private final String USER = "root";
    private final String PASSWORD = "vishwanj@2316";  // Replace if you've changed your MySQL root password

}
