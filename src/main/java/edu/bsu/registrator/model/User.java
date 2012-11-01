package edu.bsu.registrator.model;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public class User extends AbstractModel{

  private String name;
  private Contact contact;

  public User() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Contact getContact() {
    return contact;
  }

  public void setContact(Contact contact) {
    this.contact = contact;
  }
}
