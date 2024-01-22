package az.unitech.app.mapper;

import az.unitech.app.entity.UserAccount;
import az.unitech.app.model.UserAccountBaseDto;
import az.unitech.app.model.UserAccountDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T08:53:47+0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240103-0614, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class UserAccountMapperImpl extends UserAccountMapper {

    @Override
    public UserAccountBaseDto entityToNumberBaseDto(UserAccount entitiy) {
        if ( entitiy == null ) {
            return null;
        }

        UserAccountBaseDto.UserAccountBaseDtoBuilder<?, ?> userAccountBaseDto = UserAccountBaseDto.builder();

        userAccountBaseDto.createdAt( entitiy.getCreatedAt() );
        userAccountBaseDto.endDate( entitiy.getEndDate() );
        userAccountBaseDto.id( entitiy.getId() );
        userAccountBaseDto.status( entitiy.getStatus() );
        userAccountBaseDto.accountNumber( entitiy.getAccountNumber() );
        userAccountBaseDto.balance( entitiy.getBalance() );

        return userAccountBaseDto.build();
    }

    @Override
    public UserAccount numberDtoToEntity(UserAccountBaseDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserAccount userAccount = new UserAccount();

        userAccount.setCreatedAt( dto.getCreatedAt() );
        userAccount.setEndDate( dto.getEndDate() );
        userAccount.setId( dto.getId() );
        userAccount.setStatus( dto.getStatus() );
        userAccount.setAccountNumber( dto.getAccountNumber() );
        userAccount.setBalance( dto.getBalance() );

        return userAccount;
    }

    @Override
    public List<UserAccountDto> entityToNumberDtos(List<UserAccount> listEntity) {
        if ( listEntity == null ) {
            return null;
        }

        List<UserAccountDto> list = new ArrayList<UserAccountDto>( listEntity.size() );
        for ( UserAccount userAccount : listEntity ) {
            list.add( userAccountToUserAccountDto( userAccount ) );
        }

        return list;
    }

    protected UserAccountDto userAccountToUserAccountDto(UserAccount userAccount) {
        if ( userAccount == null ) {
            return null;
        }

        UserAccountDto.UserAccountDtoBuilder<?, ?> userAccountDto = UserAccountDto.builder();

        userAccountDto.createdAt( userAccount.getCreatedAt() );
        userAccountDto.endDate( userAccount.getEndDate() );
        userAccountDto.id( userAccount.getId() );
        userAccountDto.status( userAccount.getStatus() );
        userAccountDto.accountNumber( userAccount.getAccountNumber() );
        userAccountDto.balance( userAccount.getBalance() );
        userAccountDto.userId( userAccount.getUserId() );

        return userAccountDto.build();
    }
}
