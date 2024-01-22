package az.unitech.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "transaction_types", schema = "account")
@SQLDelete(sql = "UPDATE account.transaction_types SET status = '0', end_date = CURRENT_TIMESTAMP WHERE id = ?")
public class TransactionType extends BaseEntity<Long> {

    @Column(nullable = false)
    private String name;

    public TransactionType(Long id) {
        this.setId(id);
    }
}
