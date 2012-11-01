package edu.bsu.registrator.dto.impl;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 31.10.12
 */
public class CreateNewUserDTO extends SynchronizationTokenDTO {

  private List<ContactDTO> contactDTOs;

  public CreateNewUserDTO() {
  }

  public List<ContactDTO> getContactDTOs() {
    return contactDTOs;
  }

  public void setContactDTOs(List<ContactDTO> contactDTOs) {
    this.contactDTOs = contactDTOs;
  }
}
