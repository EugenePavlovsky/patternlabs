package edu.bsu.registrator.dao.impl;

import edu.bsu.registrator.dao.DAO;
import edu.bsu.registrator.model.AbstractModel;
import edu.bsu.registrator.session.Session;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 15.10.12
 */
public class BaseDAO<T extends AbstractModel> implements DAO<T> {

  private Class<T> type;
  private Session session;

  @SuppressWarnings("unchecked")
  public BaseDAO() {
    type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  }

  @Override
  public T instantiate() {
    try {
      return type.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void delete(T model) {
     session.deleteModel(model);
  }

  @Override
  public T getById(Long id) {
    return session.loadModelById(id, type);
  }

  @Override
  public List<T> getAll() {
    return session.loadAllModelsOfType(type);
  }

  @Override
  public void save(T model) {
    session.saveModel(model);
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}