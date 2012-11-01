package edu.bsu.registrator.dto.converter.xml.impl;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import edu.bsu.registrator.dto.DTO;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class BaseDtoToXmlConverter<T extends DTO> implements DtoToXmlConverter<T> {

  protected final Map<String, Class<? extends DTO>> aliasNameClassMap = new HashMap<String, Class<? extends DTO>>();

  @SuppressWarnings("unchecked")
  public BaseDtoToXmlConverter() {
    Class<? extends DTO> dtoClass = (Class<? extends DTO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    aliasNameClassMap.put("dto", dtoClass);
  }

  @Override
  public String convertToXml(T dto) {
    final XStream xStream = new XStream(new DomDriver());
    for (Map.Entry<String, Class<? extends DTO>> entry : aliasNameClassMap.entrySet()) {
      xStream.alias(entry.getKey(), entry.getValue());
    }
    return xStream.toXML(dto);
  }
}
