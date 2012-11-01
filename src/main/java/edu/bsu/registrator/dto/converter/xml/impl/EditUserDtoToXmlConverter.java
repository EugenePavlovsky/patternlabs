package edu.bsu.registrator.dto.converter.xml.impl;

import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.EditUserDTO;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class EditUserDtoToXmlConverter extends BaseDtoToXmlConverter<EditUserDTO> {

  public EditUserDtoToXmlConverter() {
    aliasNameClassMap.put("contactDTO", ContactDTO.class);
  }
}
