package az.unitech.app.mapper;

import az.unitech.app.entity.Transaction;
import az.unitech.app.entity.TransactionType;
import az.unitech.app.model.TransactionBaseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-22T08:53:48+0400",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240103-0614, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class TransactionMapperImpl extends TransactionMapper {

    @Override
    public TransactionBaseDto entityToDto(Transaction entity) {
        if ( entity == null ) {
            return null;
        }

        TransactionBaseDto.TransactionBaseDtoBuilder<?, ?> transactionBaseDto = TransactionBaseDto.builder();

        transactionBaseDto.transactionType( entityTransactionTypeName( entity ) );
        transactionBaseDto.accountNumber( entity.getAccountNumber() );
        transactionBaseDto.amount( entity.getAmount() );
        transactionBaseDto.createdAt( entity.getCreatedAt() );
        transactionBaseDto.transactionUUId( entity.getTransactionUUId() );

        return transactionBaseDto.build();
    }

    private String entityTransactionTypeName(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }
        TransactionType transactionType = transaction.getTransactionType();
        if ( transactionType == null ) {
            return null;
        }
        String name = transactionType.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
