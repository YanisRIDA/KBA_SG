package kba.model.dto;

import kba.domain.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id", "balance", "lastName", "firstName", "operations"})
public class BankAccountDTO {

    int id;
    long balance;
    String lastName;
    String firstName;
    List<Operation> operations;

}
