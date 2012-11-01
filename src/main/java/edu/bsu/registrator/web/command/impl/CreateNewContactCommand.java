package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.CreateNewContactDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.CreateNewContactDTO;
import edu.bsu.registrator.resource.ResourceManager;
import edu.bsu.registrator.util.XMLStreamUtil;
import edu.bsu.registrator.util.XSLTTransformerUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;

import java.io.OutputStream;

/**
 * @author Eugene Pavlovsky
 * @since 24.10.12
 */
public class CreateNewContactCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/new-contact.xsl";

  @Override
  public void execute() {
    try {
      final CreateNewContactDTO createNewContactDTO = new CreateNewContactDTO();
      final DtoToXmlConverter<CreateNewContactDTO> converter = new CreateNewContactDtoToXmlConverter();
      createNewContactDTO.setSynchronizationToken((String)getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(createNewContactDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }
}
