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
@ToString(of= {"id","lastName","firstName", "mail", "account"})
public class Client {

    long id;
    String lastName;
    String firstName;
    String mail;
    Password password;
    BankAccount account;
    String token;

}
