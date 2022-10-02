package kba.domain;

import kba.utils.OperationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id", "amount", "operationType", "date", "oldBalance", "newBalance"})
public class Operation {

    int id;
    long amount;
    OperationType operationType;
    Date date;
    long oldBalance;
    long newBalance;

}
