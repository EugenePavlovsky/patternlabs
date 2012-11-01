package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dto.converter.xml.impl.SynchronizationTokenDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.SynchronizationTokenDTO;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.resource.ResourceManager;
import edu.bsu.registrator.util.XMLStreamUtil;
import edu.bsu.registrator.util.XSLTTransformerUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;

import java.io.OutputStream;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class ErrorCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/error.xsl";

  @Override
  public void execute() {
    try {
      final SynchronizationTokenDTO synchronizationTokenDTO = new SynchronizationTokenDTO();
      final DtoToXmlConverter<SynchronizationTokenDTO> converter = new SynchronizationTokenDtoToXmlConverter();
      getParameter().invalidateSession();
      synchronizationTokenDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(synchronizationTokenDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }
}
