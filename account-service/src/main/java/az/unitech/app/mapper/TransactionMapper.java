package az.unitech.app.mapper;

import org.mapstruct.*;

import az.unitech.app.entity.Transaction;
import az.unitech.app.model.TransactionBaseDto;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    @Mapping(target = "transactionType", source = "transactionType.name")
    public abstract TransactionBaseDto entityToDto(Transaction entity);

}
