package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.ContactToContactDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.CreateNewUserDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.CreateNewUserDTO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.resource.ResourceManager;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.XMLStreamUtil;
import edu.bsu.registrator.util.XSLTTransformerUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 24.10.12
 */
public class CreateNewUserCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/new-user.xsl";

  private final DAO<Contact> contactDAO;

  public CreateNewUserCommand() {
    final Session session = new SessionImpl();
    contactDAO = new ContactDAO();
    contactDAO.setSession(session);
  }

  @Override
  public void execute() {
    try {
      final CreateNewUserDTO createNewUserDTO = new CreateNewUserDTO();
      final DtoToXmlConverter<CreateNewUserDTO> converter = new CreateNewUserDtoToXmlConverter();
      createNewUserDTO.setSynchronizationToken((String)getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      createNewUserDTO.setContactDTOs(getContactDTOsFromContacts(getAvailableContacts()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(createNewUserDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }

  private List<Contact> getAvailableContacts() {
    return contactDAO.getAll();
  }

  private List<ContactDTO> getContactDTOsFromContacts(List<Contact> contacts) {
    final List<ContactDTO> contactDTOs = new ArrayList<ContactDTO>(contacts.size());
    final ModelToDtoConverter<Contact, ContactDTO> converter = new ContactToContactDtoConverter();

    for (Contact contact : contacts) {
      contactDTOs.add(converter.convertToDTO(contact));
    }

    return contactDTOs;
  }
}
