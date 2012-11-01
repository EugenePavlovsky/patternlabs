package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.impl.EditContactDTO;
import edu.bsu.registrator.model.Contact;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ContactToEditContactDtoConverter implements ModelToDtoConverter<Contact, EditContactDTO> {

  public ContactToEditContactDtoConverter() {
  }

  @Override
  public EditContactDTO convertToDTO(Contact contact) {
    final EditContactDTO editContactDTO = new EditContactDTO();
    editContactDTO.setId(contact.getId());
    editContactDTO.setPhone(contact.getPhone());
    editContactDTO.setEmail(contact.getEmail());
    editContactDTO.setIcq(contact.getIcq());
    return editContactDTO;
  }
}
