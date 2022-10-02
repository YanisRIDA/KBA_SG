package kba.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"firstName", "lastName"})
public class Client {

    String firstName;
    String lastName;

}
