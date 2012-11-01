package edu.bsu.registrator.dto.converter.model.impl;

import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.model.Contact;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class ContactToContactDtoConverter implements ModelToDtoConverter<Contact, ContactDTO> {

  public ContactToContactDtoConverter() {
  }

  @Override
  public ContactDTO convertToDTO(Contact contact) {
    final ContactDTO contactDTO = new ContactDTO();
    contactDTO.setId(contact.getId());
    contactDTO.setPhone(contact.getPhone());
    contactDTO.setEmail(contact.getEmail());
    contactDTO.setIcq(contact.getIcq());
    return contactDTO;
  }
}
