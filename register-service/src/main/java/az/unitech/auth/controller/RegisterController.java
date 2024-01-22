package az.unitech.auth.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import az.unitech.auth.dto.PasswordDto;
import az.unitech.auth.dto.UserPasswordDto;
import az.unitech.auth.dto.UserDto.UserDto;
import az.unitech.auth.dto.UserDto.UserGetDto;
import az.unitech.auth.service.users.RegisterService;

/**

 The UserController class represents the REST controller for user-related operations.
 This controller is responsible for handling HTTP requests related to users, such as getting, inserting, updating and deleting user information, as well as resetting user passwords and adding user privileges.
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RegisterController {

    private final RegisterService userService;

    /**
     * Retrieves the user by their ID.
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID
     */
    @GetMapping("{id}")
    ResponseEntity<?> getByIdUsers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Inserts a new user into the system.
     * @param userDto the DTO containing the user information
     * @return a success message indicating that the user was inserted
     */
    @PostMapping
    public ResponseEntity<String> insertUser(@RequestBody UserDto userDto){
        userService.insertUser(userDto);
        return ResponseEntity.ok("New user is inserted!");
    }

    /**
     * Deletes a user with the specified ID.
     * @param id the ID of the user to delete
     * @return a success message indicating that the user was deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("The user with id: "+id+" is deleted!");
    }

    /**
     * Updates the information of an existing user.
     * @param id the ID of the user to update
     * @param userDto the DTO containing the updated user information
     * @return a success message indicating that the user was updated
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserGetDto userDto){
        userService.updateUser(id,userDto);
        return ResponseEntity.ok("The user with id: "+id+" is updated");
    }

    /**
     * Resets the password of a user with the specified ID.
     * @param id the ID of the user whose password is to be reset
     * @param password the DTO containing the new password
     * @return a success message indicating that the user's password was reset
     */
    @PostMapping("/reset/{id}")
    public ResponseEntity<String> resetPassword(@PathVariable Long id,@RequestBody PasswordDto password){
            userService.resetPassword(id, password.getPassword());
            return ResponseEntity.ok("The password of the user with id: " + id + " is resetted");
    }

    /**
     * Resets the password of a user with the specified ID.
     * @param id the ID of the user whose password is to be reset
     * @param password the DTO containing the new password
     * @return a success message indicating that the user's password was reset
     */
    @PostMapping("/reset")
    public ResponseEntity<String> resetUserPassword(@RequestBody UserPasswordDto userPassword){
            Long userId = userService.resetUserPassword(userPassword.getOldPassword(),userPassword.getNewPassword());
            if (userId==0) {
                return ResponseEntity.ok("Old and new password is equal"); 
            } else if (userId==-1){
                return ResponseEntity.ok("Old password is not correct"); 
            }
            else {
                return ResponseEntity.ok("The password of the user with id: " + userId + " is resetted");
            }
    }




}