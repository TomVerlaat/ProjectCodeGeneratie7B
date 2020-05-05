package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class Account   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("IBAN")
  private String IBAN = null;

  @JsonProperty("balance")
  private BigDecimal balance = null;

  /**
   * This describes if the account is a current account or a savings account
   */
  public enum AccountTypeEnum {
    CURRENT("current"),
    
    SAVINGS("savings");

    private String value;

    AccountTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String text) {
      for (AccountTypeEnum b : AccountTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("accountType")
  private AccountTypeEnum accountType = null;

  @JsonProperty("accountOwner")
  private Integer accountOwner = null;

  public Account id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * An unique ID every account gets
   * @return id
  **/
  @ApiModelProperty(example = "0", value = "An unique ID every account gets")
  
    public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Account IBAN(String IBAN) {
    this.IBAN = IBAN;
    return this;
  }

  /**
   * The IBAN number every account gets to transfer money, known by the owner of the account
   * @return IBAN
  **/
  @ApiModelProperty(example = "NL01INHO0000000001", value = "The IBAN number every account gets to transfer money, known by the owner of the account")
  
  @Pattern(regexp="^NL\\d{2}INHO0\\d{9}$")   public String getIBAN() {
    return IBAN;
  }

  public void setIBAN(String IBAN) {
    this.IBAN = IBAN;
  }

  public Account balance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  /**
   * balance in EU
   * @return balance
  **/
  @ApiModelProperty(example = "0", value = "balance in EU")
  
    @Valid
    public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public Account accountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * This describes if the account is a current account or a savings account
   * @return accountType
  **/
  @ApiModelProperty(example = "current", value = "This describes if the account is a current account or a savings account")
  
    public AccountTypeEnum getAccountType() {
    return accountType;
  }

  public void setAccountType(AccountTypeEnum accountType) {
    this.accountType = accountType;
  }

  public Account accountOwner(Integer accountOwner) {
    this.accountOwner = accountOwner;
    return this;
  }

  /**
   * Has an integer which specifies the ID of the account owner
   * @return accountOwner
  **/
  @ApiModelProperty(example = "0", value = "Has an integer which specifies the ID of the account owner")
  
    public Integer getAccountOwner() {
    return accountOwner;
  }

  public void setAccountOwner(Integer accountOwner) {
    this.accountOwner = accountOwner;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(this.id, account.id) &&
        Objects.equals(this.IBAN, account.IBAN) &&
        Objects.equals(this.balance, account.balance) &&
        Objects.equals(this.accountType, account.accountType) &&
        Objects.equals(this.accountOwner, account.accountOwner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, IBAN, balance, accountType, accountOwner);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Account {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    IBAN: ").append(toIndentedString(IBAN)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    accountOwner: ").append(toIndentedString(accountOwner)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
