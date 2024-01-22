package az.unitech.app.model;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountBaseDto extends BaseDto<Long> {

    private Long accountNumber;

    private BigDecimal balance;
}