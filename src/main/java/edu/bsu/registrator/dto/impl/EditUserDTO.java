package edu.bsu.registrator.dto.impl;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class EditUserDTO extends SynchronizationTokenDTO {

  private Long id;
  private String name;
  private Long oldContactId;
  private List<ContactDTO> contactDTOs;

  public EditUserDTO() {
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

  public Long getOldContactId() {
    return oldContactId;
  }

  public void setOldContactId(Long oldContactId) {
    this.oldContactId = oldContactId;
  }

  public List<ContactDTO> getContactDTOs() {
    return contactDTOs;
  }

  public void setContactDTOs(List<ContactDTO> contactDTOs) {
    this.contactDTOs = contactDTOs;
  }
}
