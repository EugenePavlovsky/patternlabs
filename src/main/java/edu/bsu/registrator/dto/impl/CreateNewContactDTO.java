package edu.bsu.registrator.dto.impl;

/**
 * @author Eugene Pavlovsky
 * @since 31.10.12
 */
public class CreateNewContactDTO extends SynchronizationTokenDTO {

  private String phone;
  private String email;
  private String icq;

  public CreateNewContactDTO() {
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
