package az.unitech.auth.service;

import az.unitech.auth.security.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {


    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setPin(claims.getSubject());
        return jwtInfoToken;
    }


}
