package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.impl.ViewContactDTO;
import edu.bsu.registrator.model.Contact;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ContactToViewContactDtoConverter implements ModelToDtoConverter<Contact, ViewContactDTO> {

  public ContactToViewContactDtoConverter() {
  }

  @Override
  public ViewContactDTO convertToDTO(Contact contact) {
    final ViewContactDTO viewContactDTO = new ViewContactDTO();
    viewContactDTO.setId(contact.getId());
    viewContactDTO.setPhone(contact.getPhone());
    viewContactDTO.setEmail(contact.getEmail());
    viewContactDTO.setIcq(contact.getIcq());
    return viewContactDTO;

  }
}
