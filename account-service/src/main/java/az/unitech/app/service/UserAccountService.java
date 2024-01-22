package az.unitech.app.service;

import az.unitech.app.model.UserAccountBaseDto;

import org.springframework.http.ResponseEntity;


public interface UserAccountService {

  
    ResponseEntity<?> getByUserId(Long userId);
    ResponseEntity<?> addUserAcoount(UserAccountBaseDto dto);  
    ResponseEntity<?> deleteUserAccount(Long id);

}
