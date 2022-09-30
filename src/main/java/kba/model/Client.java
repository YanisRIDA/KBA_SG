package kba.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import sun.security.util.Password;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"id"})
@ToString(of= {"id","lastName","firstName", "mail", "bankAccount"})
public class Client {

    long id;
    String lastName;
    String firstName;
    String mail;
    String password;
    BankAccount bankAccount;
    String token;

}
