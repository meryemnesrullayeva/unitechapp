package az.unitech.app.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Transaction.class)
public abstract class Transaction_ extends az.unitech.app.entity.BaseEntity_ {

	public static volatile SingularAttribute<Transaction, TransactionType> transactionType;
	public static volatile SingularAttribute<Transaction, String> transactionUUId;
	public static volatile SingularAttribute<Transaction, BigDecimal> amount;
	public static volatile SingularAttribute<Transaction, Long> accountNumber;

	public static final String TRANSACTION_TYPE = "transactionType";
	public static final String TRANSACTION_UU_ID = "transactionUUId";
	public static final String AMOUNT = "amount";
	public static final String ACCOUNT_NUMBER = "accountNumber";

}

