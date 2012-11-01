package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.impl.UserDTO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.model.User;

/**
 * @author Eugene Pavlovsky
 * @since 11.10.12
 */
public class UserToUserDtoConverter implements ModelToDtoConverter<User, UserDTO> {

  public UserToUserDtoConverter() {
  }

  @Override
  public UserDTO convertToDTO(User user) {
    final UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setName(user.getName());
    if (user.getContact() != null) {
      userDTO.setPhone(user.getContact().getPhone());
      userDTO.setEmail(user.getContact().getEmail());
      userDTO.setIcq(user.getContact().getIcq());
    }
    return userDTO;
  }
}
