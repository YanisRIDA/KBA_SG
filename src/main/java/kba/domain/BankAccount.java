package kba.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id","balance", "operations", "lastName", "firstName"})
public class BankAccount {

    int id;
    long balance;
    String lastName;
    String firstName;
    List<Operation> operations;

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

}
