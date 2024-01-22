package az.unitech.app.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UserAccount.class)
public abstract class UserAccount_ extends az.unitech.app.entity.BaseEntity_ {

	public static volatile SingularAttribute<UserAccount, BigDecimal> balance;
	public static volatile SingularAttribute<UserAccount, Long> accountNumber;
	public static volatile SingularAttribute<UserAccount, Long> userId;

	public static final String BALANCE = "balance";
	public static final String ACCOUNT_NUMBER = "accountNumber";
	public static final String USER_ID = "userId";

}

