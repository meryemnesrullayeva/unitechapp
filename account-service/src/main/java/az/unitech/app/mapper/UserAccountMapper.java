package az.unitech.app.mapper;
import az.unitech.app.entity.UserAccount;
import az.unitech.app.model.UserAccountBaseDto;
import az.unitech.app.model.UserAccountDto;

import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper {

    public abstract  UserAccountBaseDto entityToNumberBaseDto(UserAccount entitiy);

    public abstract  UserAccount numberDtoToEntity(UserAccountBaseDto dto);

    public abstract List<UserAccountDto> entityToNumberDtos(List<UserAccount> listEntity);

}
