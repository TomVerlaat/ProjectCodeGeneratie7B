package io.swagger.api;

import io.swagger.Service.AccountService;
import io.swagger.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountsApiController.class)

public class AccountsApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService service;
    private Account account;

    @Before
    public void setUp() {
        account = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000010", Account.TypeEnum.CURRENT,1);
    }

    @Test
    public void whenAllAccountsAreFetchedShouldReturnJsonArray() throws Exception {
        Iterable<Account> allAccounts = Arrays.asList(account);
        given(service.getAllAccounts()).willReturn(allAccounts);
        mvc.perform(get("/guitars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].brand").value(account.getId()));
    }

    @Test
    public void whenAddAccountShouldReturnCreated() throws Exception {
        mvc.perform(post("/accounts/new")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{}"))
                .andExpect(status().isCreated());
    }
}
