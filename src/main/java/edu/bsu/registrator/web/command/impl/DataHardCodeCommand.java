package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.dao.impl.ContactDAO;
import edu.bsu.registrator.dao.impl.UserDAO;
import edu.bsu.registrator.model.Contact;
import edu.bsu.registrator.model.User;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.SessionImpl;
import edu.bsu.registrator.web.exception.InfrastructureException;

/**
 * @author Eugene Pavlovsky
 * @since 24.10.12
 */
public class DataHardCodeCommand extends BaseCommand {

  @Override
  public void execute() {
    try {
      final Session session = new SessionImpl();
      final DAO<Contact> contactDAO = new ContactDAO();
      contactDAO.setSession(session);
      final DAO<User> userDAO = new UserDAO();
      userDAO.setSession(session);

      final Contact contact1 = contactDAO.instantiate();
      contact1.setPhone("253453245");
      contact1.setEmail("fedor@rambler.ru");
      contact1.setIcq("2453427875");
      contactDAO.save(contact1);

      final Contact contact2 = contactDAO.instantiate();
      contact2.setPhone("4398762134");
      contact2.setEmail("silva@gmail.com");
      contact2.setIcq("2398463429");
      contactDAO.save(contact2);

      final Contact contact3 = contactDAO.instantiate();
      contact3.setPhone("35789867369");
      contact3.setEmail("lyoto.m@mail.ru");
      contact3.setIcq("783435867879");
      contactDAO.save(contact3);

      final Contact contact4 = contactDAO.instantiate();
      contact4.setPhone("3489643823");
      contact4.setEmail("johny@hotmail.com");
      contact4.setIcq("56979125474");
      contactDAO.save(contact4);

      final Contact contact5 = contactDAO.instantiate();
      contact5.setPhone("43875636235");
      contact5.setEmail("jds@gmail.com");
      contact5.setIcq("349867352634");
      contactDAO.save(contact5);

      final Contact contact6 = contactDAO.instantiate();
      contact6.setPhone("9348572356");
      contact6.setEmail("cain@yandex.ru");
      contact6.setIcq("13345576435");
      contactDAO.save(contact6);

      final Contact contact7 = contactDAO.instantiate();
      contact7.setPhone("14568534556");
      contact7.setEmail("diaz@hotmail.com");
      contact7.setIcq("56873457689");
      contactDAO.save(contact7);

      final Contact contact8 = contactDAO.instantiate();
      contact8.setPhone("457436123532");
      contact8.setEmail("overoid@mail.ru");
      contact8.setIcq("34643634");
      contactDAO.save(contact8);

      final Contact contact9 = contactDAO.instantiate();
      contact9.setPhone("23568895468");
      contact9.setEmail("sombat@gmail.com");
      contact9.setIcq("2387536653946");
      contactDAO.save(contact9);

      final Contact contact10 = contactDAO.instantiate();
      contact10.setPhone("5552343434");
      contact10.setEmail("cormier@gmail.com");
      contact10.setIcq("237492341");
      contactDAO.save(contact10);

      final User user1 = userDAO.instantiate();
      user1.setName("Fedor Emelianenko");
      user1.setContact(contact1);
      userDAO.save(user1);

      final User user2 = userDAO.instantiate();
      user2.setName("Anderson Silva");
      user2.setContact(contact1);
      userDAO.save(user2);

      final User user3 = userDAO.instantiate();
      user3.setName("Lyoto Machida");
      user3.setContact(contact1);
      userDAO.save(user3);

      final User user4 = userDAO.instantiate();
      user4.setName("John Jones");
      user4.setContact(contact1);
      userDAO.save(user4);

      final User user5 = userDAO.instantiate();
      user5.setName("Junior Dos Santos");
      user5.setContact(contact1);
      userDAO.save(user5);

      final User user6 = userDAO.instantiate();
      user6.setName("Cain Velasquez");
      user6.setContact(contact6);
      userDAO.save(user6);

      final User user7 = userDAO.instantiate();
      user7.setName("Nick Diaz");
      user7.setContact(contact7);
      userDAO.save(user7);

      final User user8 = userDAO.instantiate();
      user8.setName("Alistar Overeem");
      user8.setContact(contact8);
      userDAO.save(user8);

      final User user9 = userDAO.instantiate();
      user9.setName("Buakaw Por Pramuk");
      user9.setContact(contact9);
      userDAO.save(user9);

      final User user10 = userDAO.instantiate();
      user10.setName("Daniel Cormier");
      user10.setContact(contact10);
      userDAO.save(user10);

      session.commit();
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }
}
