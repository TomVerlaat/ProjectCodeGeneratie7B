package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * Body
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2020-05-05T07:37:10.859Z[GMT]")
public class LoginBody {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  public LoginBody username(String username) {
    this.username = username;
    return this;
  }

  /**
   * The username of the person logging in
   * @return username
  **/
  @ApiModelProperty(example = "Gebruiker1", value = "The username of the person logging in")
  
    public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LoginBody password(String password) {
    this.password = password;
    return this;
  }

  /**
   * The password of the person logging in
   * @return password
  **/
  @ApiModelProperty(example = "Welkom01!", value = "The password of the person logging in")
  
    public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginBody body = (LoginBody) o;
    return Objects.equals(this.username, body.username) &&
        Objects.equals(this.password, body.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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
