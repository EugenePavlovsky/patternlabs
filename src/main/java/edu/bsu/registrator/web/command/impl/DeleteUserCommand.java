package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.model.User;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.DataConversionUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;
import edu.bsu.registrator.web.helper.handler.HomePageHandler;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class DeleteUserCommand extends BaseCommand {

  private final Session session;
  private final DAO<User> userDAO;

  public DeleteUserCommand() {
    session = new SessionImpl();
    userDAO = new UserDAO();
  }

  @Override
  public void execute() {
    final Long userId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("userId"));
    if (userId != null) {
      User user = userDAO.getById(userId);
      userDAO.delete(user);
      session.commit();
    } else {
      throw new InfrastructureException("Incorrect parameter value.");
    }

    HomePageHandler.processHomePage(getParameter());
  }
}
