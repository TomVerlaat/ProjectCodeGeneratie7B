package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-14T18:16:38.158Z[GMT]")
public class UpdateBody {
    @JsonProperty("username")
    private String username = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("confirmPassword")
    private String confirmPassword = null;

    @JsonProperty("firstName")
    private String firstName = null;

    @JsonProperty("lastName")
    private String lastName = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("birthdate")
    private LocalDate birthdate = null;

    @JsonProperty("address")
    private String address = null;

    @JsonProperty("postalcode")
    private String postalcode = null;

    @JsonProperty("city")
    private String city = null;

    @JsonProperty("phoneNumber")
    private String phoneNumber = null;

    @JsonProperty("type")
    private User.Type type = null;

    @JsonProperty("transactionLimit")
    private double transactionLimit;

    @JsonProperty("maximumDebt")
    private double maximumDebt;

    /**
     * Gets or Sets type
     */
    @ApiModelProperty(example = "100000", value = "")

    public double getTransactionLimit() {
        return transactionLimit;
    }

    public void setTransactionLimit(double transactionLimit) {
        this.transactionLimit = transactionLimit;
    }

    /**
     * Get maximumDebt
     * @return maximumDebt
     **/
    @ApiModelProperty(example = "-100", value = "")

    public double getMaximumDebt() {
        return maximumDebt;
    }

    public void setMaximumDebt(double maximumDebt) {
        this.maximumDebt = maximumDebt;
    }

    public UpdateBody username(String username) {
        this.username = username;
        return this;
    }

    /**
     * Get username
     * @return username
     **/
    @ApiModelProperty(example = "MaxVerstappen", value = "")
    @NotNull

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get password
     * @return password
     **/
    @ApiModelProperty(example = "Password123", value = "")
    @NotNull

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get confirmpassword
     * @return confirmpassword
     **/
    @ApiModelProperty(example = "Password123", value = "")

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UpdateBody firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Get firstName
     * @return firstName
     **/
    @ApiModelProperty(example = "Max", value = "")
    @NotNull

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UpdateBody lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Get lastName
     * @return lastName
     **/
    @ApiModelProperty(example = "Verstappen", value = "")
    @NotNull

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UpdateBody email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     * @return email
     **/
    @ApiModelProperty(example = "verstappen@jumbo.nl", value = "")
    @NotNull

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UpdateBody birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    /**
     * Get birthdate
     * @return birthdate
     **/
    @ApiModelProperty(example = "Sun Nov 16 00:00:00 GMT 10", value = "")
    @NotNull

    @Valid
    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public UpdateBody address(String address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     * @return address
     **/
    @ApiModelProperty(example = "Alphenlaat 42", value = "")
    @NotNull

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UpdateBody postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    /**
     * Get postalcode
     * @return postalcode
     **/
    @ApiModelProperty(example = "2041 KP", value = "")
    @NotNull

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public UpdateBody city(String city) {
        this.city = city;
        return this;
    }

    /**
     * Get city
     * @return city
     **/
    @ApiModelProperty(example = "Zandvoort", value = "")
    @NotNull

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public UpdateBody phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    /**
     * Get phoneNumber
     * @return phoneNumber
     **/
    @ApiModelProperty(example = "069876543210", value = "")
    @NotNull

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UpdateBody type(User.Type type) {
        this.type = type;
        return this;
    }

    /**
     * Get type
     * @return type
     **/
    @ApiModelProperty(example = "Customer", value = "")
    @NotNull

    public User.Type getType() {
        return type;
    }

    public void setType(User.Type type) {
        this.type = type;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UpdateBody body4 = (UpdateBody) o;
        return Objects.equals(this.username, body4.username) &&
                Objects.equals(this.password, body4.password) &&
                Objects.equals(this.firstName, body4.firstName) &&
                Objects.equals(this.lastName, body4.lastName) &&
                Objects.equals(this.email, body4.email) &&
                Objects.equals(this.birthdate, body4.birthdate) &&
                Objects.equals(this.address, body4.address) &&
                Objects.equals(this.postalcode, body4.postalcode) &&
                Objects.equals(this.city, body4.city) &&
                Objects.equals(this.phoneNumber, body4.phoneNumber) &&
                Objects.equals(this.type, body4.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName, email, birthdate, address, postalcode, city, phoneNumber, type);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Body4 {\n");

        sb.append("    username: ").append(toIndentedString(username)).append("\n");
        sb.append("    password: ").append(toIndentedString(password)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
        sb.append("    postalcode: ").append(toIndentedString(postalcode)).append("\n");
        sb.append("    city: ").append(toIndentedString(city)).append("\n");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
