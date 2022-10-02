package kba.dto;

import kba.utils.OperationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"amount", "operationType", "date", "oldBalance", "newBalance"})
public class OperationDTO {

    long amount;
    OperationType operationType;
    Date date;
    long oldBalance;
    long newBalance;

}
