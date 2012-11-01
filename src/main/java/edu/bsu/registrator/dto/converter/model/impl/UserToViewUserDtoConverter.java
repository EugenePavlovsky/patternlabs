package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.impl.ViewUserDTO;
import edu.bsu.registrator.model.User;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class UserToViewUserDtoConverter implements ModelToDtoConverter<User, ViewUserDTO> {

  public UserToViewUserDtoConverter() {
  }

  @Override
  public ViewUserDTO convertToDTO(User user) {
    final ViewUserDTO viewUserDTO = new ViewUserDTO();
    viewUserDTO.setId(user.getId());
    viewUserDTO.setName(user.getName());
    if (user.getContact() != null) {
      viewUserDTO.setPhone(user.getContact().getPhone());
      viewUserDTO.setEmail(user.getContact().getEmail());
      viewUserDTO.setIcq(user.getContact().getIcq());
    }
    return viewUserDTO;
  }
}
