package edu.bsu.registrator.dto.impl;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class ContactDTO extends IdentifierDTO {

  private String phone;
  private String email;
  private String icq;

  public ContactDTO() {
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIcq() {
    return icq;
  }

  public void setIcq(String icq) {
    this.icq = icq;
  }
}
