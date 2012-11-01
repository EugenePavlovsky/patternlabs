package edu.bsu.registrator.dto.converter.xml.impl;

import edu.bsu.registrator.dto.impl.UserDTO;
import edu.bsu.registrator.dto.impl.ViewUsersDTO;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewUsersDtoToXmlConverter extends BaseDtoToXmlConverter<ViewUsersDTO> {

  public ViewUsersDtoToXmlConverter() {
    aliasNameClassMap.put("userDTO", UserDTO.class);
  }
}
