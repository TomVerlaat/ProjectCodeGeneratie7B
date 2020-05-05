package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body4 Gregors test tekst
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class WithdrawBody {
  @JsonProperty("account")
  private String account = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("userPerformingId")
  private Integer userPerformingId = null;

  public WithdrawBody account(String account) {
    this.account = account;
    return this;
  }

  /**
   * The IBAN number
   * @return account
  **/
  @ApiModelProperty(example = "NL01INHO0000000001", value = "The IBAN number")
  
  @Pattern(regexp="^NL\\d{2}INHO0\\d{9}$")   public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public WithdrawBody amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amount of money which is withdrawed
   * @return amount
  **/
  @ApiModelProperty(example = "19.95", value = "The amount of money which is withdrawed")
  
    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public WithdrawBody userPerformingId(Integer userPerformingId) {
    this.userPerformingId = userPerformingId;
    return this;
  }

  /**
   * The ID of the user performing the transaction
   * @return userPerformingId
  **/
  @ApiModelProperty(example = "1", value = "The ID of the user performing the transaction")
  
    public Integer getUserPerformingId() {
    return userPerformingId;
  }

  public void setUserPerformingId(Integer userPerformingId) {
    this.userPerformingId = userPerformingId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WithdrawBody body4 = (WithdrawBody) o;
    return Objects.equals(this.account, body4.account) &&
        Objects.equals(this.amount, body4.amount) &&
        Objects.equals(this.userPerformingId, body4.userPerformingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account, amount, userPerformingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body4 {\n");
    
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    userPerformingId: ").append(toIndentedString(userPerformingId)).append("\n");
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
