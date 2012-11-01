package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.UserToUserDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.ViewUsersDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.UserDTO;
import edu.bsu.registrator.dto.impl.ViewUsersDTO;
import edu.bsu.registrator.model.User;
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
public class ViewUsersCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/view-users.xsl";

  private final DAO<User> userDAO;

  public ViewUsersCommand() {
    final Session session = new SessionImpl();
    userDAO = new UserDAO();
    userDAO.setSession(session);
  }

  @Override
  public void execute() {
    try {
      final ViewUsersDTO viewUsersDTO = new ViewUsersDTO();
      viewUsersDTO.setUserDTOs(getUserDTOsFromUsers(getAvailableUsers()));
      final DtoToXmlConverter<ViewUsersDTO> converter = new ViewUsersDtoToXmlConverter();
      viewUsersDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(converter.convertToXml(viewUsersDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }

  private List<User> getAvailableUsers() {
    return userDAO.getAll();
  }

  private List<UserDTO> getUserDTOsFromUsers(List<User> users) {
    final List<UserDTO> userDTOs = new ArrayList<UserDTO>(users.size());
    final ModelToDtoConverter<User, UserDTO> converter = new UserToUserDtoConverter();

    for (User user : users) {
      userDTOs.add(converter.convertToDTO(user));
    }
    return userDTOs;
  }
}
