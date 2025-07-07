package Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReturnedBookManagement {
    private int returnID;
    private  int issueID ;
    private  int bookID ;
    private int studentID;
    private  Date issueDate ;
    private Date returnDate;
    private  int quantityRecieved ;
}
