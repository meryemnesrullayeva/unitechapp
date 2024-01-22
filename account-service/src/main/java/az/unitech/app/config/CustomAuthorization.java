package az.unitech.app.config;


import az.unitech.app.errors.ErrorsFinal;
import az.unitech.app.exception.ApplicationException;
import az.unitech.app.model.AuthorizeRequest;
import az.unitech.app.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomAuthorization {

    private final String secret;
    private final HttpServletRequest httpServletRequest;
    private final AuthService authService;
    private final String authUrl;

    public CustomAuthorization(@Value("${jwt.secret}") String secret, HttpServletRequest httpServletRequest, AuthService authService,
                               @Value("${auth.url}") String authUrl) {
        this.secret = secret;
        this.httpServletRequest = httpServletRequest;
        this.authService = authService;
        this.authUrl = authUrl;
    }

    public Long getUserIdFromToken() {
        try {

            String authorizationHeader = httpServletRequest.getHeader("Authorization");

            String accessToken = authorizationHeader.substring("Bearer ".length());
            Claims claims = authService.getClaims(accessToken);


            return Long.parseLong(claims.get("userId").toString());
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Token");
        }
    }

   
    
    /**

     Checks if the user is authorized to access a certain method by sending an authorization request to an external API.
     @param methodName a String representing the name of the method to be checked for authorization
     @return a boolean indicating whether the user is authorized to access the method or not
     @throws JsonProcessingException if there is an error while processing JSON data
     @throws ApplicationException if the user is not authorized to access the method
     */
    public boolean isValid(String methodName) throws JsonProcessingException {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String accessToken = authorizationHeader.substring("Bearer ".length());

        AuthorizeRequest jwtRequest = new AuthorizeRequest(accessToken,methodName);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(jwtRequest);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<Boolean> response = restTemplate.exchange(
                authUrl,
                HttpMethod.POST,
                requestEntity,
                Boolean.class
        );

        boolean authorized = Boolean.TRUE.equals(response.getBody());
        if (!authorized) {
            throw new ApplicationException(ErrorsFinal.ACCESS_DENIED);
        }

        return authorized;

    }

}
