package az.unitech.app.entity;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts", schema = "account")
@SQLDelete(sql = "update account.accounts set status='0', end_date=current_timestamp where id =?")
public class UserAccount extends BaseEntity<Long> {

    @Column
    private Long userId;

    @Column
    private Long accountNumber;

    @Column
    private BigDecimal balance;

    public UserAccount(Long id) {
        this.setId(id);
    }
}
