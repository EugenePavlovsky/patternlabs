package edu.bsu.registrator.dto.converter.xml.impl;

import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.CreateNewUserDTO;

/**
 * @author Eugene Pavlovsky
 * @since 31.10.12
 */
public class CreateNewUserDtoToXmlConverter extends BaseDtoToXmlConverter<CreateNewUserDTO> {

  public CreateNewUserDtoToXmlConverter() {
    aliasNameClassMap.put("contactDTO", ContactDTO.class);
  }
}
