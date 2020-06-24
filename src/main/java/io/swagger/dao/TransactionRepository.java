package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.threeten.bp.OffsetDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> getAllByUserPerformingId(double value);

    Transaction getById(Long value);

    List<Transaction> getByAccountFromOrAccountToOrderByTimestampDesc(String value1,String value2);

    @Query("select count(s.id) from Transaction s where s.timestamp >= ?1")
    int getTransactionsToday(OffsetDateTime date);

}

