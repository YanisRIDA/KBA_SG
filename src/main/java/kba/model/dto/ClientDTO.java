package kba.model.dto;

import kba.model.BankAccount;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sun.security.util.Password;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"lastName","firstName", "mail"})
public class ClientDTO {

    String lastName;
    String firstName;
    String password;
    String mail;

}
