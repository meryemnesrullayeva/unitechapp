package az.unitech.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto extends BaseDto<Long> {
    private Long userId;

    private Long accountNumber;

    private BigDecimal balance;
}
