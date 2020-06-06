package io.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.Service.AccountService;
import io.swagger.model.Account;/*
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean private AccountService service;
    private Account account;

    /*@BeforeEach
    public void setup() {
        account = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000010", Account.TypeEnum.CURRENT,1);;
    }

    @Test
    public void getAllAccountsShouldReturnJsonArray() throws Exception {
        given(service.getAllAccounts()).willReturn(Arrays.asList(account));
        this.mvc.perform(get("/Accounts")).andExpect(
                status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].iban").value(account.getBrand()));
    }

    @Test
    public void callingAllAccountsShouldReturnOK() throws Exception {
        this.mvc.perform(get("/Accounts"))
                .andExpect(status().isOk());
    }

    @Test
    public void postingAnAccountShouldReturn201Created() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Account account = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000010", Account.TypeEnum.CURRENT,1);
        this.mvc
                .perform(
                        post("/Accounts/new")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(account)))
                                .andExpect(status().isCreated());
    }
*/
}
