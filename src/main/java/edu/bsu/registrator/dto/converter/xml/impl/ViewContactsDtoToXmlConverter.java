package edu.bsu.registrator.dto.converter.xml.impl;

import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.ViewContactsDTO;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewContactsDtoToXmlConverter extends BaseDtoToXmlConverter<ViewContactsDTO> {

  public ViewContactsDtoToXmlConverter() {
    aliasNameClassMap.put("contactDTO", ContactDTO.class);
  }
}
