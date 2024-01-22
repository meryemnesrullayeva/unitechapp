package az.unitech.auth.converter;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import az.unitech.auth.dto.UserDto.UserDto;
import az.unitech.auth.dto.UserDto.UserGetDto;
import az.unitech.auth.entity.User;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Mapping(target = "password", qualifiedByName = "passwordEncoding")
    public abstract User toEntity(UserDto dto);

    public abstract UserDto toDto(User user);

    public abstract List<UserGetDto> getDtoList(List<User> listUsers);

    public abstract List<UserDto> toDtos(List<User> listUsers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void update(@MappingTarget User user, UserGetDto dto);

    @Named("passwordEncoding")
    String passwordEncoding(String password) {
        return passwordEncoder.encode(password);
    }


}
