package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class TransactionBody {
  @JsonProperty("timestamp")
  private String timestamp = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("amount")
  private BigDecimal amount = null;

  @JsonProperty("userPerformingId")
  private Integer userPerformingId = null;

  public TransactionBody timestamp(String timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * The date and time the transaction took place
   * @return timestamp
  **/
  @ApiModelProperty(example = "2017-07-21T17:32:28Z", value = "The date and time the transaction took place")
  
  @Pattern(regexp="date-time")   public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public TransactionBody accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * The IBAN number
   * @return accountFrom
  **/
  @ApiModelProperty(example = "NL01INHO0000000001", value = "The IBAN number")
  
  @Pattern(regexp="^NL\\d{2}INHO0\\d{9}$")   public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public TransactionBody accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

  /**
   * The IBAN number
   * @return accountTo
  **/
  @ApiModelProperty(example = "NL01INHO0000000001", value = "The IBAN number")
  
  @Pattern(regexp="^NL\\d{2}INHO0\\d{9}$")   public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public TransactionBody amount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  /**
   * The amount of money which is transferred
   * @return amount
  **/
  @ApiModelProperty(example = "19.95", value = "The amount of money which is transferred")
  
    @Valid
    public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public TransactionBody userPerformingId(Integer userPerformingId) {
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
    TransactionBody body2 = (TransactionBody) o;
    return Objects.equals(this.timestamp, body2.timestamp) &&
        Objects.equals(this.accountFrom, body2.accountFrom) &&
        Objects.equals(this.accountTo, body2.accountTo) &&
        Objects.equals(this.amount, body2.amount) &&
        Objects.equals(this.userPerformingId, body2.userPerformingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, accountFrom, accountTo, amount, userPerformingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body2 {\n");
    
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
