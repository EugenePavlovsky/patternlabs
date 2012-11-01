package edu.bsu.registrator.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class XMLStreamUtil {

  private static final String EMPTY_XML_FILENAME = "empty.xml";

  public static final String EMPTY_XML_CONTENT = readEmptyXML();

  public static InputStream getStreamFromXML(String xml) {
    return IOUtils.toInputStream(xml);
  }

  private static String readEmptyXML() {
    String xmlContent;
    try {
      xmlContent = FileUtils.readFileToString(new File(XMLStreamUtil.class.getResource(EMPTY_XML_FILENAME).toURI()));
    } catch (Exception e) {
      return null;
    }
    return xmlContent;
  }
}
