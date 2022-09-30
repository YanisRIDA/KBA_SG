package kba.model.dto;

import kba.model.Operation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"balance", "operations"})
public class BankAccountDTO {

    long balance;
    List<Operation> operations;

}
