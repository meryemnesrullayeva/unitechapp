package az.unitech.app.controller;

import az.unitech.app.errors.SuccessMessage;
import az.unitech.app.logging.annotation.LogExecutionTime;
import az.unitech.app.model.UserAccountBaseDto;
import az.unitech.app.response.MessageResponse;
import az.unitech.app.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user-account")
@Tag(name = "UserAccount", description = "the Account api")
@SecurityRequirement(name = "bearerAuth")
public class UserAccountController {

    @Autowired
    private UserAccountService service;

   

    @LogExecutionTime
    @Operation(summary = "add account to User", description = "add account to User", tags = {"UserAccount"})
    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid UserAccountBaseDto dto){
        return MessageResponse.response(SuccessMessage.SUCCESS_ADD, service.addUserAcoount(dto), null, HttpStatus.OK);
    }


    @LogExecutionTime
    @Operation(summary = "get user accounts", description = "there you can get account number by user id", tags = {"UserAccount"})
    @GetMapping("/{userId}")
    public ResponseEntity<?> getByUserId(@NotNull @PathVariable("userId") Long id){
        return service.getByUserId(id);
    }

    @LogExecutionTime
    @Operation(summary = "delete user account", description = "there you can  delete user account ", tags = {"UserAccount"})
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUserAccount(@PathVariable Long id){
        return service.deleteUserAccount(id);
    }
}
