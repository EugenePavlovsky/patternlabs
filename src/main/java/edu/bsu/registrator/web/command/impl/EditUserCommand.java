package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.ContactToContactDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.UserToEditUserDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.EditUserDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.EditUserDTO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.model.User;
import edu.bsu.registrator.resource.ResourceManager;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.DataConversionUtil;
import edu.bsu.registrator.util.XMLStreamUtil;
import edu.bsu.registrator.util.XSLTTransformerUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class EditUserCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/edit-user.xsl";

  private final DAO<Contact> contactDAO;
  private final DAO<User> userDAO;

  public EditUserCommand() {
    final Session session = new SessionImpl();
    contactDAO = new ContactDAO();
    userDAO = new UserDAO();
    contactDAO.setSession(session);
    userDAO.setSession(session);
  }

  @Override
  public void execute() {
    try {
      final Long userId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("userId"));
      User user;
      if (userId != null) {
        user = userDAO.getById(userId);
      } else {
        throw new InfrastructureException("Incorrect parameter value.");
      }

      final ModelToDtoConverter<User, EditUserDTO> dtoConverter = new UserToEditUserDtoConverter();
      final EditUserDTO editUserDTO = dtoConverter.convertToDTO(user);
      editUserDTO.setContactDTOs(getContactDTOsFromContacts(getAvailableContacts()));
      final DtoToXmlConverter<EditUserDTO> converter = new EditUserDtoToXmlConverter();
      editUserDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));
      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(editUserDTO)),
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
