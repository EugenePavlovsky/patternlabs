package edu.bsu.registrator.util;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class XSLTTransformerUtil {

  public static void transform(InputStream xmlSourceStream, InputStream xslSourceStream, OutputStream resultStream) throws TransformerException {
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    javax.xml.transform.Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslSourceStream));
    transformer.transform(new StreamSource(xmlSourceStream), new StreamResult(resultStream));
  }
}
