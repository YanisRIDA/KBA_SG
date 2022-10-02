package kba.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id", "balance", "client", "operations"})
public class BankAccountDTO {

    int id;
    long balance;
    ClientDTO client;
    List<OperationDTO> operations;

}
