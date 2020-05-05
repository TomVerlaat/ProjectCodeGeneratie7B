package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class Transaction   {
  @JsonProperty("timestamp")
  private String timestamp = null;

  @JsonProperty("accountFrom")
  private Integer accountFrom = null;

  @JsonProperty("accountTo")
  private Integer accountTo = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("userPerformingId")
  private Integer userPerformingId = null;

  public Transaction timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * The Date and Time the transaction took place
   * @return timestamp
  **/
  @ApiModelProperty(example = "2017-07-21T17:32:28Z", value = "The Date and Time the transaction took place")
  
  @Pattern(regexp="date-time")   public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public Transaction accountFrom(Integer accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * The ID of the account from which the money is reduced
   * @return accountFrom
  **/
  @ApiModelProperty(example = "0", value = "The ID of the account from which the money is reduced")
  
    public Integer getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(Integer accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Transaction accountTo(Integer accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * The ID of the account to which the money is added
   * @return accountTo
  **/
  @ApiModelProperty(example = "0", value = "The ID of the account to which the money is added")
  
    public Integer getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(Integer accountTo) {
    this.accountTo = accountTo;
  }

  public Transaction amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amount of money which is transferred
   * @return amount
  **/
  @ApiModelProperty(example = "0", value = "The amount of money which is transferred")
  
    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Transaction userPerformingId(Integer userPerformingId) {
    this.userPerformingId = userPerformingId;
    return this;
  }

  /**
   * The ID of the user performing the transaction
   * @return userPerformingId
  **/
  @ApiModelProperty(example = "0", value = "The ID of the user performing the transaction")
  
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
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.timestamp, transaction.timestamp) &&
        Objects.equals(this.accountFrom, transaction.accountFrom) &&
        Objects.equals(this.accountTo, transaction.accountTo) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.userPerformingId, transaction.userPerformingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, accountFrom, accountTo, amount, userPerformingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
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
