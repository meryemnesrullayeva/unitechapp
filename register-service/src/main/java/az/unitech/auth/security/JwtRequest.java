package az.unitech.auth.security;

import lombok.Getter;
import lombok.Setter;

/**

 The JwtRequest class is a DTO (Data Transfer Object) used for requesting a JWT (JSON Web Token) from the server.
 It contains the username and password of the user.
 **/

@Setter
@Getter
public class JwtRequest {

    private String pin;
    private String password;

}
