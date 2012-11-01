package edu.bsu.registrator.dto.impl;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewUsersDTO extends SynchronizationTokenDTO {

  private List<UserDTO> userDTOs;

  public ViewUsersDTO() {
  }

  public List<UserDTO> getUserDTOs() {
    return userDTOs;
  }

  public void setUserDTOs(List<UserDTO> userDTOs) {
    this.userDTOs = userDTOs;
  }
}
