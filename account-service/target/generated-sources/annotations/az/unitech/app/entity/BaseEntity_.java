package az.unitech.app.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, LocalDateTime> createdAt;
	public static volatile SingularAttribute<BaseEntity, LocalDateTime> endDate;
	public static volatile SingularAttribute<BaseEntity, Serializable> id;
	public static volatile SingularAttribute<BaseEntity, Character> status;

	public static final String CREATED_AT = "createdAt";
	public static final String END_DATE = "endDate";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

