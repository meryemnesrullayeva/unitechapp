package az.unitech.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import az.unitech.app.entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

   
}
