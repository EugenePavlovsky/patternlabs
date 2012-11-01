package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.ContactToEditContactDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.EditContactDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.EditContactDTO;
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
public class EditContactCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/edit-contact.xsl";

  private final DAO<Contact> contactDAO;

  public EditContactCommand() {
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

      final ModelToDtoConverter<Contact, EditContactDTO> dtoConverter = new ContactToEditContactDtoConverter();
      final EditContactDTO editContactDTO = dtoConverter.convertToDTO(contact);
      final DtoToXmlConverter<EditContactDTO> xmlConverter = new EditContactDtoToXmlConverter();
      editContactDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(xmlConverter.convertToXml(editContactDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }
}
