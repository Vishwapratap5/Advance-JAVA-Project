package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BooksManagement {
    private int BookID;
    private String BookName;
    private String Auther;
    private String Category;
    private int TotalQuantity;
    private int AvailableQuantity;
}
