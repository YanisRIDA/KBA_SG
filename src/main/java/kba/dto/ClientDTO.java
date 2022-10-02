package kba.dto;

import kba.utils.OperationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"firstName", "lastName"})
public class ClientDTO {

    String firstName;
    String lastName;

}
