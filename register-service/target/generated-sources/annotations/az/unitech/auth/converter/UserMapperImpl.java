package az.unitech.auth.converter;

import az.unitech.auth.dto.UserDto.UserDto;
import az.unitech.auth.dto.UserDto.UserGetDto;
import az.unitech.auth.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T08:54:44+0400",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240103-0614, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public User toEntity(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( passwordEncoding( dto.getPassword() ) );
        user.setId( dto.getId() );
        user.setAddress( dto.getAddress() );
        user.setCreatedAt( dto.getCreatedAt() );
        user.setEmail( dto.getEmail() );
        user.setFatherName( dto.getFatherName() );
        user.setMobile( dto.getMobile() );
        user.setName( dto.getName() );
        user.setNote( dto.getNote() );
        user.setPin( dto.getPin() );
        user.setStatus( dto.getStatus() );
        user.setSurname( dto.getSurname() );

        return user;
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setAddress( user.getAddress() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setEmail( user.getEmail() );
        userDto.setFatherName( user.getFatherName() );
        userDto.setId( user.getId() );
        userDto.setMobile( user.getMobile() );
        userDto.setName( user.getName() );
        userDto.setNote( user.getNote() );
        userDto.setPin( user.getPin() );
        userDto.setStatus( user.getStatus() );
        userDto.setSurname( user.getSurname() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }

    @Override
    public List<UserGetDto> getDtoList(List<User> listUsers) {
        if ( listUsers == null ) {
            return null;
        }

        List<UserGetDto> list = new ArrayList<UserGetDto>( listUsers.size() );
        for ( User user : listUsers ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<UserDto> toDtos(List<User> listUsers) {
        if ( listUsers == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( listUsers.size() );
        for ( User user : listUsers ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public void update(User user, UserDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getAddress() != null ) {
            user.setAddress( dto.getAddress() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getFatherName() != null ) {
            user.setFatherName( dto.getFatherName() );
        }
        if ( dto.getMobile() != null ) {
            user.setMobile( dto.getMobile() );
        }
        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getNote() != null ) {
            user.setNote( dto.getNote() );
        }
        if ( dto.getPassword() != null ) {
            user.setPassword( dto.getPassword() );
        }
        if ( dto.getPin() != null ) {
            user.setPin( dto.getPin() );
        }
        if ( dto.getStatus() != null ) {
            user.setStatus( dto.getStatus() );
        }
        if ( dto.getSurname() != null ) {
            user.setSurname( dto.getSurname() );
        }
    }

    @Override
    public void update(User user, UserGetDto dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        if ( dto.getAddress() != null ) {
            user.setAddress( dto.getAddress() );
        }
        if ( dto.getCreatedAt() != null ) {
            user.setCreatedAt( dto.getCreatedAt() );
        }
        if ( dto.getEmail() != null ) {
            user.setEmail( dto.getEmail() );
        }
        if ( dto.getFatherName() != null ) {
            user.setFatherName( dto.getFatherName() );
        }
        if ( dto.getMobile() != null ) {
            user.setMobile( dto.getMobile() );
        }
        if ( dto.getName() != null ) {
            user.setName( dto.getName() );
        }
        if ( dto.getNote() != null ) {
            user.setNote( dto.getNote() );
        }
        if ( dto.getPin() != null ) {
            user.setPin( dto.getPin() );
        }
        if ( dto.getStatus() != null ) {
            user.setStatus( dto.getStatus() );
        }
        if ( dto.getSurname() != null ) {
            user.setSurname( dto.getSurname() );
        }
    }
}
