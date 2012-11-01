package edu.bsu.registrator.dto.impl;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewContactsDTO extends SynchronizationTokenDTO {

  private List<ContactDTO> contactDTOs;

  public ViewContactsDTO() {
  }

  public List<ContactDTO> getContactDTOs() {
    return contactDTOs;
  }

  public void setContactDTOs(List<ContactDTO> contactDTOs) {
    this.contactDTOs = contactDTOs;
  }
}
