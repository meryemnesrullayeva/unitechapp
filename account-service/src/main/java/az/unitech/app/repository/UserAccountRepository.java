package az.unitech.app.repository;

import az.unitech.app.entity.UserAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("select  p from UserAccount p where p.status = '1' and p.userId=:userId")
    List<UserAccount> getAccountByUserId(Long userId);

    @Query("select  p from UserAccount p where p.accountNumber = :accountNumber and p.status='1'")
    List<UserAccount> getByNumber(Long accountNumber);

    @Query("select  p from UserAccount p where p.accountNumber = :accountNumber and p.status=:status")
    List<UserAccount> getByNumberAndStatus(Long accountNumber, char status);

    List<UserAccount> getByAccountNumber(Long accountNumber);
}
