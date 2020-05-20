package io.swagger.Service;

import io.swagger.dao.TransactionRepository;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

@Autowired
private TransactionRepository transactionRepository;

public TransactionService() {
}

    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    public List<Transaction> getTransactionDetails(Long id) { return (List<Transaction>) transactionRepository.findOne(id);
    }

}
