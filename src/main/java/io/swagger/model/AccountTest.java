package io.swagger.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void createAccountShouldNotBeNull(){
        Account account = new Account();
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
        account.setIban("NL01INHO000000000100");
        Assert.assertTrue(account.getIban().matches("NL\\d{2}INHO0\\d{10}"));
    }
}
