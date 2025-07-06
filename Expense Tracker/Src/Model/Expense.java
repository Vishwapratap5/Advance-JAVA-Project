package Model;

import lombok.*;
import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Expense {
    private int transactionID;
    private String transactionCategory;
    private double transactionAmount;
    private String transactionDescription;
    private Date transactionDate;
}
