package edu.bsu.registrator.dto.impl;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewUserDTO extends SynchronizationTokenDTO {

  private Long id;
  private String name;
  private String phone;
  private String email;
  private String icq;

  public ViewUserDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
