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
@ToString(of= {"id","balance","client","operations"})
public class BankAccount {

    int id;
    long balance;
    Client client;
    List<Operation> operations;

    public void addOperation(Operation operation) {
        operations.add(operation);
    }

}
