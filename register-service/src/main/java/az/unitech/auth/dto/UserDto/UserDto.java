package az.unitech.auth.dto.UserDto;

import lombok.Data;

@Data
public class UserDto extends UserGetDto{
    private String password;
}
