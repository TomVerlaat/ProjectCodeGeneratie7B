package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * TransferBody
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
public class TransferBody {

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Get accountTo
   * @return accountTo
  **/
  @ApiModelProperty(example = "NL01INHO0000000000", value = "")
      @NotNull
  
    public String getAccountTo() {
    return accountTo;
  }

  public void setAccountTo(String accountTo) {
    this.accountTo = accountTo;
  }

  public TransferBody amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get accountFrom
   * @return accountFrom
   **/
  @ApiModelProperty(example = "NL01INHO0000000000", value = "")
  @NotNull

  public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }


  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(example = "100", value = "")
      @NotNull
  
    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  /**
   * Get description
   * @return description
   **/
  @ApiModelProperty(example = "Money for your new RB-17", value = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TransferBody depositBody = (TransferBody) o;
    return
        Objects.equals(this.accountTo, depositBody.accountTo) &&
        Objects.equals(this.amount, depositBody.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hash( accountTo, amount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
