package edu.bsu.registrator.dto.converter.xml;

import edu.bsu.registrator.dto.DTO;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public interface DtoToXmlConverter<T extends DTO> {

  String convertToXml(T dto);
}
