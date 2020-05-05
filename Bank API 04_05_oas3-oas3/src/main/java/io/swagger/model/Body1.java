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
 * Body1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class Body1   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("birthdate")
  private String birthdate = null;

  @JsonProperty("bsn")
  private Integer bsn = null;

  public Body1 username(String username) {
    this.username = username;
    return this;
  }

  /**
   * The username of the new user
   * @return username
  **/
  @ApiModelProperty(example = "Gebruiker1", value = "The username of the new user")
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Body1 password(String password) {
    this.password = password;
    return this;
  }

  /**
   * The password of the new user
   * @return password
  **/
  @ApiModelProperty(example = "Welkom01!", value = "The password of the new user")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Body1 email(String email) {
    this.email = email;
    return this;
  }

  /**
   * The email of the new user
   * @return email
  **/
  @ApiModelProperty(example = "user@gmail.com", value = "The email of the new user")
  
    public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Body1 birthdate(String birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  /**
   * Birthdate of the new user
   * @return birthdate
  **/
  @ApiModelProperty(example = "1990-07-21", value = "Birthdate of the new user")
  
  @Pattern(regexp="date")   public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public Body1 bsn(Integer bsn) {
    this.bsn = bsn;
    return this;
  }

  /**
   * The bsn of the new user
   * @return bsn
  **/
  @ApiModelProperty(example = "987654321", value = "The bsn of the new user")
  
    public Integer getBsn() {
    return bsn;
  }

  public void setBsn(Integer bsn) {
    this.bsn = bsn;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body1 body1 = (Body1) o;
    return Objects.equals(this.username, body1.username) &&
        Objects.equals(this.password, body1.password) &&
        Objects.equals(this.email, body1.email) &&
        Objects.equals(this.birthdate, body1.birthdate) &&
        Objects.equals(this.bsn, body1.bsn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, email, birthdate, bsn);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    birthdate: ").append(toIndentedString(birthdate)).append("\n");
    sb.append("    bsn: ").append(toIndentedString(bsn)).append("\n");
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
