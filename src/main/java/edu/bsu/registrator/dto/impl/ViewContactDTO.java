package edu.bsu.registrator.dto.impl;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewContactDTO extends SynchronizationTokenDTO {

  private Long id;
  private String phone;
  private String email;
  private String icq;

  public ViewContactDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
