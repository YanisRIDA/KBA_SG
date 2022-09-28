package kba.model;

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
@ToString(of= {"id", "amount", "operationType", "date"})
public class Operation {

    long id;
    long amount;
    OperationType operationType;
    Date date;

}
