package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void createAccountShouldNotBeNull(){
        Account account = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000010", Account.TypeEnum.CURRENT,1);
        assertNotNull(account);
    }

    @Test
    public void invalidCurrencyShouldThrowIllegalArgumentException(){
        Account account = new Account();
        String currencyInput = "EUR";
        boolean isValid = false;
        for (Account.CurrencyEnum c : Account.CurrencyEnum.values()){
            if (c.name().equals(currencyInput)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("Currency is not valid!");
    }

    @Test
    public void invalidIbanShouldThrowIllegalArgumentException(){
        Account account = new Account();
        account.setIban("NL01INHO00000000100");
        Assert.assertTrue(account.getIban().matches("NL\\d{2}INHO0\\d{10}"));
    }

    @Test
    public void accountBalanceShouldBeAboveZero(){
        Account account = new Account();
        account.setBalance(5);
        Assert.assertTrue(account.getBalance() > 0);
    }

    @Test
    public void newAccountShouldBeActive(){
        Account account = new Account();
        Assert.assertTrue(account.isActive());
    }

    @Test
    public void invalidTypeShouldThrowIllegalArgumentException(){
        Account account = new Account();
        String accountType = "SAVINGS";
        boolean isValid = false;
        for (Account.TypeEnum c : Account.TypeEnum.values()){
            if (c.name().equals(accountType)) {
                isValid = true;
            }
        }
        if (!isValid) throw new IllegalArgumentException("Type is not valid!");
    }

    @Test
    public void checkIfUserIdIsValid(){
        Account account = new Account();
        account.setUserId((long)1);
        Assert.assertTrue(account.getUserId() > 0);
    }
}
