package edu.bsu.registrator.session;

import edu.bsu.registrator.model.AbstractModel;

import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 21.10.12
 */
public interface Session {

  <T extends AbstractModel> void saveModel(T model);

  <T extends AbstractModel> void deleteModel(T model);

  <T extends AbstractModel> List<T> loadAllModelsOfType(Class<T> type);

  <T extends AbstractModel> T loadModelById(Long id, Class<T> type);

  void commit();

  void rollback();
}
