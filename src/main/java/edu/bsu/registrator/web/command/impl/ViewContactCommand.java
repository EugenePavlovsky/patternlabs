package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.ContactToViewContactDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.ViewContactDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.ViewContactDTO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.resource.ResourceManager;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.DataConversionUtil;
import edu.bsu.registrator.util.XMLStreamUtil;
import edu.bsu.registrator.util.XSLTTransformerUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class ViewContactCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/view-contact.xsl";

  private final DAO<Contact> contactDAO;

  public ViewContactCommand() {
    final Session session = new SessionImpl();
    contactDAO = new ContactDAO();
    contactDAO.setSession(session);
  }

  @Override
  public void execute() {
    try {
      final Long contactId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("contactId"));
      Contact contact;
      if (contactId != null) {
        contact = contactDAO.getById(contactId);
      } else {
        throw new InfrastructureException("Incorrect parameter value.");
      }

      final ModelToDtoConverter<Contact, ViewContactDTO> dtoConverter = new ContactToViewContactDtoConverter();
      final ViewContactDTO viewContactDTO = dtoConverter.convertToDTO(contact);
      final DtoToXmlConverter<ViewContactDTO> xmlConverter = new ViewContactDtoToXmlConverter();
      viewContactDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(xmlConverter.convertToXml(viewContactDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }
}
