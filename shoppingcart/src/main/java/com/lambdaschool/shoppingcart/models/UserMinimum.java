package com.lambdaschool.shoppingcart.models;

import javax.validation.constraints.Email;

public class UserMinimum {
  /**
   * The username (String)
   */
  private String username;

  /**
   * The user's password (String)
   */
  private String password;

  @Email
  private String primaryemail;

  /**
   * Getter for the username
   *
   * @return the username (String) associated with this user
   */
  public String getUsername()
  {
    return username;
  }

  /**
   * Setter for the username
   *
   * @param username the new username (String) associated with this user
   */
  public void setUsername(String username)
  {
    this.username = username;
  }

  /**
   * Getter for the password of this user
   *
   * @return the password (String) for this user
   */
  public String getPassword()
  {
    return password;
  }

  /**
   * Setter for the password of this user. This object is a temporary model used to create a new user.
   * The password must remain in clear text until saved into the database.
   *
   * @param password the new password (String in clear texts) for this user
   */
  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPrimaryemail() {
    return primaryemail;
  }

  public void setPrimaryemail(String primaryemail) {
    this.primaryemail = primaryemail;
  }
}
