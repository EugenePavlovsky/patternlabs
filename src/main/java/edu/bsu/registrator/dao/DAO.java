package edu.bsu.registrator.dao;

import edu.bsu.registrator.model.AbstractModel;
import edu.bsu.registrator.session.Session;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 15.10.12
 */
public interface DAO<T extends AbstractModel> {

  T instantiate();

  T getById(Long id);

  void save(T model);

  void delete(T model);

  List<T> getAll();

  Session getSession();

  void setSession(Session session);

}