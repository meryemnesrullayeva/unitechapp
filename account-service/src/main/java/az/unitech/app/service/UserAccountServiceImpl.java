package az.unitech.app.service;

import az.unitech.app.entity.UserAccount;
import az.unitech.app.errors.ErrorsFinal;
import az.unitech.app.errors.SuccessMessage;
import az.unitech.app.exception.ApplicationException;
import az.unitech.app.mapper.UserAccountMapper;
import az.unitech.app.model.UserAccountBaseDto;
import az.unitech.app.model.UserAccountDto;
import az.unitech.app.repository.UserAccountRepository;
import az.unitech.app.response.MessageResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @PersistenceContext
    EntityManager entityManager;
    

    @Override
    public ResponseEntity<?> getByUserId(Long userId) {
        List<UserAccount> userAccounts = userAccountRepository.getAccountByUserId(userId);
        List<UserAccountDto> dtos = userAccountMapper.entityToNumberDtos(userAccounts);
        return MessageResponse.response(SuccessMessage.SUCCESS_GET, dtos, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUserAcoount(UserAccountBaseDto dto) {
      
        List<UserAccount> userAccounts=userAccountRepository.getByNumber(dto.getAccountNumber());
        if (!userAccounts.isEmpty()) {
            throw new ApplicationException(ErrorsFinal.ACCOUNT_NUMBER_EXISTS,
            Map.ofEntries(Map.entry("accountNumber", dto.getAccountNumber())));
        }

        UserAccount userAccount = userAccountMapper.numberDtoToEntity(dto);
        userAccountRepository.save(userAccount);
        UserAccountBaseDto posDto = userAccountMapper.entityToNumberBaseDto(userAccount);
        return MessageResponse.response(SuccessMessage.SUCCESS_ADD, posDto, null, HttpStatus.OK);
    }

  
    @Override
    public ResponseEntity<?> deleteUserAccount(Long id) {
        UserAccount userAccount = userAccountRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorsFinal.DATA_NOT_FOUND,
                        Map.ofEntries(Map.entry("id", id), Map.entry("account", "number"))));
        userAccountRepository.delete(userAccount);
        UserAccountBaseDto userAccountBaseDto = userAccountMapper.entityToNumberBaseDto(userAccount);
        return MessageResponse.response(SuccessMessage.SUCCESS_DELETE, userAccountBaseDto, null, HttpStatus.OK);
    }

}
