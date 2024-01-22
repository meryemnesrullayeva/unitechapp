package az.unitech.auth.dto.UserDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class UserGetDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String pin;
    private String name;
    private String surname;
    private String fatherName;
    private String address;
    private String mobile;
    private String email;
    private String note;
    private Character status;
    private LocalDateTime createdAt;

}
