package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.util.DataConversionUtil;
import edu.bsu.registrator.web.exception.InfrastructureException;
import edu.bsu.registrator.web.helper.handler.HomePageHandler;

/**
 * @author Eugene Pavlovsky
 * @since 01.11.12
 */
public class DeleteContactCommand extends BaseCommand {

  private final DAO<Contact> contactDAO;
  private final Session session;

  public DeleteContactCommand() {
    session = new SessionImpl();
    contactDAO = new ContactDAO();
    contactDAO.setSession(session);
  }

  @Override
  public void execute() {
    final Long contactId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("contactId"));
    if (contactId != null) {
      Contact contact = contactDAO.getById(contactId);
      contactDAO.delete(contact);
      session.commit();
    } else {
      throw new InfrastructureException("Incorrect parameter value.");
    }

    HomePageHandler.processHomePage(getParameter());
  }
}
