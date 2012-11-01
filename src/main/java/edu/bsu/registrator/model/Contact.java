package edu.bsu.registrator.model;

/**
 * @author Eugene Pavlovsky
 * @since 11.10.12
 */
public class Contact extends AbstractModel {

  private String phone;
  private String email;
  private String icq;

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
