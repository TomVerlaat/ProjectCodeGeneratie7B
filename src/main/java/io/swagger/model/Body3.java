package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body3
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
public class Body3   {
  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("userPerformingId")
  private Long userPerformingId = null;

  public Body3 accountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
    return this;
  }

  /**
   * Get accountFrom
   * @return accountFrom
  **/
  @ApiModelProperty(example = "NL01INHO0000000000", value = "")
  
    public String getAccountFrom() {
    return accountFrom;
  }

  public void setAccountFrom(String accountFrom) {
    this.accountFrom = accountFrom;
  }

  public Body3 amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(example = "100", value = "")
  
    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Body3 description(String description) {
    this.description = description;
    return this;
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

  public Body3 userPerformingId(Long userPerformingId) {
    this.userPerformingId = userPerformingId;
    return this;
  }

  /**
   * Get userPerformingId
   * @return userPerformingId
  **/
  @ApiModelProperty(example = "10000000001", value = "")
  
    public Long getUserPerformingId() {
    return userPerformingId;
  }

  public void setUserPerformingId(Long userPerformingId) {
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
    Body3 body3 = (Body3) o;
    return Objects.equals(this.accountFrom, body3.accountFrom) &&
        Objects.equals(this.amount, body3.amount) &&
        Objects.equals(this.description, body3.description) &&
        Objects.equals(this.userPerformingId, body3.userPerformingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountFrom, amount, description, userPerformingId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body3 {\n");
    
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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
