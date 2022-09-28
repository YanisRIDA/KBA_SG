package kba.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id","balance", "operations"})
public class BankAccount {

    long id;
    long balance;
    List<Operation> operations;

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

}
