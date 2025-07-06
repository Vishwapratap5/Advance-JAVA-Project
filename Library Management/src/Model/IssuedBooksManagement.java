package Model;

import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class IssuedBooksManagement {
    private  int issuedID;
    private int issuedBooksID;
    private int studentID;
    private Date issuedDate;
    private Date expectedReturnDate;
    private int  quantityIssued;
}
