package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.web.helper.handler.HomePageHandler;

/**
 * @author Eugene Pavlovsky
 * @since 25.10.12
 */
public class SaveNewContactCommand extends BaseCommand {

  public SaveNewContactCommand() {
  }

  @Override
  public void execute() {
    final Session session = new SessionImpl();
    final DAO<Contact> contactDAO = new ContactDAO();
    contactDAO.setSession(session);

    final Contact contact= contactDAO.instantiate();
    updateContactFields(contact);

    contactDAO.save(contact);
    session.commit();

    HomePageHandler.processHomePage(getParameter());
  }

  private void updateContactFields(Contact contact){
    contact.setPhone(getParameter().getParameter("phone"));
    contact.setEmail(getParameter().getParameter("email"));
    contact.setIcq(getParameter().getParameter("icq"));
  }
}
