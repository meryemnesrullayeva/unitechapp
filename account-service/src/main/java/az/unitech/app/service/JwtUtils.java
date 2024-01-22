package az.unitech.app.service;

import az.unitech.app.model.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


/**

 The JwtUtils class provides static methods for generating a JwtAuthentication object from a set of claims
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    /**
     * Generates a JwtAuthentication object from a set of claims
     * @param claims - the claims used to generate the JwtAuthentication object
     * @return - the generated JwtAuthentication object
     */
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setPin(claims.getSubject());
        return jwtInfoToken;
    }

    // /**
    //  * Generates a set of GrantedAuthority objects from a set of claims
    //  * @param claims - the claims used to generate the set of GrantedAuthority objects
    //  * @return - the generated set of GrantedAuthority objects
    //  */
    // private static Set<GrantedAuthority> getPermissions(Claims claims) {
    //     final List roles = claims.get("roles", List.class);
    //     final Set<GrantedAuthority> rolesSet = new HashSet<>();

    //     for (Object o : roles) {
    //         LinkedHashMap<String, String> l = (LinkedHashMap<String, String>) o;
    //         rolesSet.add((GrantedAuthority) () -> l.get("roleName"));
    //     }
    //     return rolesSet;
    // }

}
