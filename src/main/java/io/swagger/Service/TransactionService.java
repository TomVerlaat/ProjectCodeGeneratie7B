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

    public List<Transaction> getTransactionByUserId(double userId) {
        return (List<Transaction>) transactionRepository.getAllByUserPerformingId(userId);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.getById(id);
    }


    public List<Transaction> GetTransactionsFromIban(String iban)
    {
        return transactionRepository.getByAccountFromOrAccountToOrderByTimestampDesc(iban,iban);
    }

    public void addTransaction(Transaction transaction)
    {
        transactionRepository.save(transaction);
    }
}
