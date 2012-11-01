package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.model.User;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.DataConversionUtil;
import edu.bsu.registrator.web.helper.handler.HomePageHandler;

/**
 * @author Eugene Pavlovsky
 * @since 25.10.12
 */
public class SaveNewUserCommand extends BaseCommand {

  private final Session session;
  private final DAO<Contact> contactDAO;
  private final DAO<User> userDAO;

  public SaveNewUserCommand() {
    session = new SessionImpl();
    contactDAO = new ContactDAO();
    userDAO = new UserDAO();
    userDAO.setSession(session);
    contactDAO.setSession(session);
  }

  @Override
  public void execute() {
    final User user = userDAO.instantiate();

    updateUserFields(user);

    userDAO.save(user);
    session.commit();

    HomePageHandler.processHomePage(getParameter());
  }

  private void updateUserFields(User user) {
    final Long contactId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("contact"));

    if (contactId != null) {
      user.setContact(contactDAO.getById(contactId));
    }

    user.setName(getParameter().getParameter("name"));
  }


}
