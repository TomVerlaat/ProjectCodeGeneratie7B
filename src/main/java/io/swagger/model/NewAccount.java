package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Account
 */
@Entity
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
public class NewAccount   {
    public NewAccount(){}

    public NewAccount(CurrencyEnum currency, String iban, Account.TypeEnum type, Long userId){
        //Static properties
        setActive(true);
        setBalance(0);

        //Dynamic properties
        setCurrency(currency);
        setIban(iban);
        setType(type);
        setUserId(userId);
    }

    @Id
    @SequenceGenerator(name = "account_seq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("userId")
    private Long userId = null;

    /**
     * Gets or Sets type

    public enum TypeEnum {
        SAVINGS("Savings"),

        CURRENT("Current");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static TypeEnum fromValue(String text) {
            for (TypeEnum b : TypeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
     */
    @JsonProperty("type")
    private Account.TypeEnum type = null;

    /**
     * Gets or Sets currency
     */
    public enum CurrencyEnum {
        EUR("EUR");

        private String value;

        CurrencyEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CurrencyEnum fromValue(String text) {
            for (CurrencyEnum b : CurrencyEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }
    @JsonProperty("currency")
    private CurrencyEnum currency = null;

    @JsonProperty("balance")
    private Double balance = null;

    @JsonProperty("active")
    private Boolean active = null;

    @JsonProperty("iban")
    private String iban = null;

    public NewAccount id(Long id) {
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

    public NewAccount userId(Long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Get userId
     * @return userId
     **/
    @ApiModelProperty(example = "10000000002", required = true, value = "")
    @NotNull

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public NewAccount type(Account.TypeEnum type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @ApiModelProperty(example = "Savings", required = true, value = "")
    @NotNull

    public Account.TypeEnum getType() {
        return type;
    }

    public void setType(Account.TypeEnum type) {
        this.type = type;
    }

    public NewAccount currency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    /**
     * Get currency
     * @return currency
     **/
    @ApiModelProperty(example = "EUR", required = true, value = "")
    @NotNull

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public NewAccount balance(double balance) {
        this.balance = balance;
        return this;
    }

    /**
     * Get balance
     * @return balance
     **/
    @ApiModelProperty(value = "")

    @Valid
    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public NewAccount active(Boolean active) {
        this.active = active;
        return this;
    }

    /**
     * Get active
     * @return active
     **/
    @ApiModelProperty(example = "true", required = true, value = "")
    @NotNull

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public NewAccount iban(String iban) {
        this.iban = iban;
        return this;
    }

    /**
     * Get iban
     * @return iban
     **/
    @ApiModelProperty(example = "NLxxINHO0xxxxxxxxx", value = "")

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewAccount account = (NewAccount) o;
        return Objects.equals(this.id, account.id) &&
                Objects.equals(this.userId, account.userId) &&
                Objects.equals(this.type, account.type) &&
                Objects.equals(this.currency, account.currency) &&
                Objects.equals(this.balance, account.balance) &&
                Objects.equals(this.active, account.active) &&
                Objects.equals(this.iban, account.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, type, currency, balance, active, iban);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    active: ").append(toIndentedString(active)).append("\n");
        sb.append("    iban: ").append(toIndentedString(iban)).append("\n");
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
