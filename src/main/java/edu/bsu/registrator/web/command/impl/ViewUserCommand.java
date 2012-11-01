package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.dto.converter.model.ModelToDtoConverter;
import edu.bsu.registrator.dto.converter.model.impl.UserToViewUserDtoConverter;
import edu.bsu.registrator.dto.converter.xml.DtoToXmlConverter;
import edu.bsu.registrator.dto.converter.xml.impl.ViewUserDtoToXmlConverter;
import edu.bsu.registrator.dto.impl.ViewUserDTO;
import edu.bsu.registrator.model.User;
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
public class ViewUserCommand extends BaseCommand {

  private static final String XSLT_SCRIPT_PATH = "xsl/view-user.xsl";

  private final DAO<User> userDAO;

  public ViewUserCommand() {
    final Session session = new SessionImpl();
    userDAO = new UserDAO();
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

      final ModelToDtoConverter<User, ViewUserDTO> dtoConverter = new UserToViewUserDtoConverter();
      final ViewUserDTO viewUserDTO = dtoConverter.convertToDTO(user);
      final DtoToXmlConverter<ViewUserDTO> xmlConverter = new ViewUserDtoToXmlConverter();
      viewUserDTO.setSynchronizationToken((String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey()));

      XSLTTransformerUtil.transform(XMLStreamUtil.getStreamFromXML(xmlConverter.convertToXml(viewUserDTO)),
                                    ResourceManager.getResourceAsStream(XSLT_SCRIPT_PATH),
                                    getParameter().getOutputStream());
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }


  }
}
