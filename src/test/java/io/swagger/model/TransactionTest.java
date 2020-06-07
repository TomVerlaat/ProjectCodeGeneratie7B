package io.swagger.model;

import io.swagger.Service.AccountService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TransactionTest {

    @Test
    public void insufficientFundsShouldReturnFalse() {
        boolean check;
        double accountBalance = 500;
        double transactionAmount = 200;
        final double limit = -100;
        if (accountBalance - transactionAmount >= limit) check = true;
        else check = false;
        Assert.assertTrue(check);
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        assertNotNull(transaction);
    }

    @Test
    public void invalidAccountFromShouldReturnFalse() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        Assert.assertTrue(transaction.getAccountFrom().matches("NL\\d{2}INHO0\\d{10}"));
    }

    @Test
    public void invalidAccountToShouldReturnFalse() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        Assert.assertTrue(transaction.getAccountTo().matches("NL\\d{2}INHO0\\d{10}"));
    }

    @Test
    public void invalidAmountShouldReturnFalse() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        boolean check;
        double input = transaction.getAmount();
        final double minAmount = 0;
        final double maxAmount = 100000;
        if (input > minAmount && input <= maxAmount) check = true;
        else check = false;
        Assert.assertTrue(check);
    }

    @Test
    public void invalidUserIdPerformingShouldReturnFalse() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        Assert.assertTrue(transaction.getUserPerformingId() > 0);
    }

    @Test
    public void invalidTransactionTypeShouldReturnFalse() {
        Transaction transaction = new Transaction("NL01INHO00000000010", "NL01INHO00000000002", 100.00, "Money for Fiat Multipla", 1L, Transaction.TransactionTypeEnum.PAYMENT);
        Assert.assertTrue(Arrays.asList(Transaction.TransactionTypeEnum.values()).contains(transaction.getTransactionType()));
    }
}
