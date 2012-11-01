package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.ContactToContactDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.ViewContactsDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.ContactDTO;
import edu.bsu.registrator.dto.impl.ViewContactsDTO;
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
 * @since 01.11.12
 */
public class ViewContactsCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/view-contacts.xsl";

  private DAO<Contact> contactDAO;

  public ViewContactsCommand() {
    final Session session = new SessionImpl();
    contactDAO = new ContactDAO();
    contactDAO.setSession(session);
  }

  @Override
  public void execute() {
    try {
      final ViewContactsDTO viewContactsDTO = new ViewContactsDTO();
      viewContactsDTO.setContactDTOs(getContactDTOsFromContacts(getAvailableContacts()));
      final DtoToXmlConverter<ViewContactsDTO> converter = new ViewContactsDtoToXmlConverter();
      viewContactsDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(viewContactsDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }

  public List<Contact> getAvailableContacts() {
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
