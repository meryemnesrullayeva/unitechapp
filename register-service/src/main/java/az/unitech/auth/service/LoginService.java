package az.unitech.auth.service;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import az.unitech.auth.entity.*;
import az.unitech.auth.exception.ApplicationException;
import az.unitech.auth.exception.error.Errors;
import az.unitech.auth.security.AuthRequest;
import az.unitech.auth.security.JwtAuthentication;
import az.unitech.auth.security.JwtRequest;
import az.unitech.auth.security.JwtResponse;
import az.unitech.auth.service.users.RegisterService;

import java.util.*;


@Service
@RequiredArgsConstructor
public class LoginService {


    private final UserJWTService userJWTService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final RegisterService userService;
    private final HttpServletRequest httpServletRequest;
  
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userJWTService.getByPin(authRequest.getPin())
                .orElseThrow(() -> new ApplicationException(Errors.PIN_NOT_FOUND));

        if (user.getStatus()=='1'&&new BCryptPasswordEncoder().matches(authRequest.getPassword(), user.getPassword())) {
            final String accessToken = jwtProvider.generateAccessToken(user);
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getPin(), refreshToken);
            return new JwtResponse(user.getId(),accessToken, refreshToken);
        } else {
            throw new ApplicationException(Errors.INCORRECT_PASSWORD);
        }
    }

    public String getPinFromToken(){
        try {

            String authorizationHeader = httpServletRequest.getHeader("Authorization");

            String accessToken = authorizationHeader.substring("Bearer ".length());
            Claims claims = jwtProvider.getAccessClaims(accessToken);

            Long userId =  Long.parseLong(claims.get("userId").toString());

            String pin = userService.getPinByUserId(userId);
            return pin;
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Token");
        }
    }


    public JwtResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String pin = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(pin);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userJWTService.getByPin(pin)
                        .orElseThrow(() -> new AuthException("User not found"));
                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.remove(user.getPin());
                refreshStorage.put(user.getPin(), refreshToken);
                return new JwtResponse(user.getId(),accessToken,null);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(username);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userJWTService.getByPin(username)
                        .orElseThrow(() -> new AuthException("User not found"));

                final String accessToken = jwtProvider.generateAccessToken(user);
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getPin(), newRefreshToken);
                return new JwtResponse(user.getId(),null,newRefreshToken);
            }
        }
        throw new AuthException("Invalid JWT token");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public Boolean isAuthorized(AuthRequest authRequest){
       
        return true;

    }

}
