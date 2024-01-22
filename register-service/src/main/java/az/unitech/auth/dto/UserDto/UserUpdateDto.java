package az.unitech.auth.dto.UserDto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserUpdateDto {

    private String pin;
    private String name;
    private String surname;
    private String fatherName;
    private String status;
}
