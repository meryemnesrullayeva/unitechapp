package az.unitech.auth.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserPasswordDto {
    private String oldPassword;
    private String newPassword;
}
