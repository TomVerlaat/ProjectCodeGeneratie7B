package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
public class Transaction   {
  @Id
  @SequenceGenerator(name = "transaction_seq", initialValue = 1000001)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp = null;

  @JsonProperty("accountFrom")
  private String accountFrom = null;

  @JsonProperty("accountTo")
  private String accountTo = null;

  @JsonProperty("amount")
  private Double amount = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("userPerformingId")
  private Long userPerformingId = null;

  //Constructor
  public Transaction(String accountFrom, String accountTo, Double amount, String description, Long userPerformingId, TransactionTypeEnum transactionType) {
    setTimestamp(OffsetDateTime.now());
    setAccountFrom(accountFrom);
    setAccountTo(accountTo);
    setAmount(amount);
    setDescription(description);
    setUserPerformingId(userPerformingId);
    setTransactionType(transactionType);
  }

  public Transaction() {
    setTimestamp(OffsetDateTime.now());
  }

  /**
   * Gets or Sets transactionType
   */
  public enum TransactionTypeEnum {
    DEPOSIT("Deposit"),
    
    WITHDRAWAL("Withdrawal"),
    
    TRANSFER("Transfer"),

    PAYMENT("Payment");

    private String value;

    TransactionTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TransactionTypeEnum fromValue(String text) {
      for (TransactionTypeEnum b : TransactionTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  //@JsonProperty("transactionType")
  @Enumerated(EnumType.STRING)
  private TransactionTypeEnum transactionType = null;

  public Transaction id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "10000000001", value = "")
  
    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Get timestamp
   * @return timestamp
  **/
  @ApiModelProperty(example = "2020-05-07T12:32:28Z", required = true, value = "")
      @NotNull

    @Valid
    public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public Transaction accountFrom(String accountFrom) {
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

  public Transaction accountTo(String accountTo) {
    this.accountTo = accountTo;
    return this;
  }

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

  public Transaction amount(Double amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   * @return amount
  **/
  @ApiModelProperty(example = "100", required = true, value = "")
      @NotNull

    public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Transaction description(String description) {
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

  public Transaction userPerformingId(Long userPerformingId) {
    this.userPerformingId = userPerformingId;
    return this;
  }

  /**
   * Get userPerformingId
   * @return userPerformingId
  **/
  @ApiModelProperty(example = "10000000001", required = true, value = "")
      //@NotNull

    public Long getUserPerformingId() {
    return userPerformingId;
  }

  public void setUserPerformingId(Long userPerformingId) {
    this.userPerformingId = userPerformingId;
  }

  public Transaction transactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
    return this;
  }

  /**
   * Get transactionType
   * @return transactionType
  **/
  @ApiModelProperty(example = "Deposit", required = true, value = "")
      //@NotNull

    public TransactionTypeEnum getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(TransactionTypeEnum transactionType) {
    this.transactionType = transactionType;
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
    return Objects.equals(this.id, transaction.id) &&
        Objects.equals(this.timestamp, transaction.timestamp) &&
        Objects.equals(this.accountFrom, transaction.accountFrom) &&
        Objects.equals(this.accountTo, transaction.accountTo) &&
        Objects.equals(this.amount, transaction.amount) &&
        Objects.equals(this.description, transaction.description) &&
        Objects.equals(this.userPerformingId, transaction.userPerformingId) &&
        Objects.equals(this.transactionType, transaction.transactionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timestamp, accountFrom, accountTo, amount, description, userPerformingId, transactionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    accountFrom: ").append(toIndentedString(accountFrom)).append("\n");
    sb.append("    accountTo: ").append(toIndentedString(accountTo)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    userPerformingId: ").append(toIndentedString(userPerformingId)).append("\n");
    sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
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
