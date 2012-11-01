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
public class SaveContactCommand extends BaseCommand {

  public SaveContactCommand() {
  }

  @Override
  public void execute() {
    final Session session = new SessionImpl();
    final DAO<Contact> contactDAO = new ContactDAO();
    contactDAO.setSession(session);

    final Long contactId = DataConversionUtil.convertFromStringToLong(getParameter().getParameter("contactId"));
    Contact contact;
    if (contactId != null) {
      contact = contactDAO.getById(contactId);
    } else {
      throw new InfrastructureException("Incorrect parameter value.");
    }
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