package io.swagger.IT.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.*;
import io.swagger.model.Account;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class StepDefinitions {


  RestTemplate template = new RestTemplate();
  ResponseEntity<String> responseEntity;
  String response;

  HttpHeaders headers = new HttpHeaders();
  String baseUrl = "http://localhost:8080/Groep7B/BankAPI_V3/1.0.0/Accounts";

  @When("I retrieve all accounts")
  public void iRetrieveAllAccounts() throws URISyntaxException {
    URI uri = new URI(baseUrl);
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    responseEntity = template.getForEntity(uri, String.class);
  }

  @Then("I get http status {int}")
  public void iGetHttpStatus(int status) {
    Assert.assertEquals(responseEntity.getStatusCodeValue(), status);
  }

  @Then("I get a list of {int} accounts")
  public void iGetAListOfAccounts(int size) throws JSONException {
    response = responseEntity.getBody();
    JSONArray array = new JSONArray(response);
    Assert.assertEquals(size, array.length());
  }

  @When("I retrieve account with id {int}")
  public void iRetrieveAccountWithId(int id) throws URISyntaxException {
    URI uri = new URI(baseUrl + "/" + id);
    responseEntity = template.getForEntity(uri, String.class);
  }

  @Then("The account iban is {string}")
  public void theAccountIbanIs(String iban) throws JSONException {
    response = responseEntity.getBody();
    Assert.assertEquals(iban,
        new JSONObject(response)
            .getString("iban"));
  }

  @When("I post an account")
  public void iPostAnAccount() throws JsonProcessingException, URISyntaxException {
    ObjectMapper mapper = new ObjectMapper();
    Account account = new Account(500, Account.CurrencyEnum.EUR, "NL01INHO00000000010", Account.TypeEnum.CURRENT,1);
    URI uri = new URI(baseUrl);
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> entity = new HttpEntity<>(mapper.writeValueAsString(account), headers);
    responseEntity = template.postForEntity(uri, entity, String.class);
  }
}
