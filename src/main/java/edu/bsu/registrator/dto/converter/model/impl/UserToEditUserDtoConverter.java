package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.impl.EditUserDTO;
import edu.bsu.registrator.model.User;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class UserToEditUserDtoConverter implements ModelToDtoConverter<User, EditUserDTO> {

  public UserToEditUserDtoConverter() {
  }

  @Override
  public EditUserDTO convertToDTO(User user) {
    final EditUserDTO editUserDTO = new EditUserDTO();
    editUserDTO.setId(user.getId());
    editUserDTO.setName(user.getName());
    if (user.getContact() != null) {
      editUserDTO.setOldContactId(user.getContact().getId());
    }
    return editUserDTO;
  }
}
