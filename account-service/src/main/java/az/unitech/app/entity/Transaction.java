package az.unitech.app.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "transactions", schema = "account")
@SQLDelete(sql = "UPDATE account.transactions SET status = '0', end_date = CURRENT_TIMESTAMP WHERE id = ?")
public class Transaction extends BaseEntity<Long>{
    @Column(name="transaction_uuid",nullable = false)
    private String transactionUUId;

    @Column(nullable = false)
    private Long accountNumber;
    
    @Column
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private TransactionType transactionType;

    public Transaction(Long id) {
        this.setId(id);
    }
}
