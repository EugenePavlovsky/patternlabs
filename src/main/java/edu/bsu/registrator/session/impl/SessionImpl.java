package edu.bsu.registrator.session.impl;

import edu.bsu.registrator.model.AbstractModel;
import edu.bsu.registrator.session.Session;
import edu.bsu.registrator.session.impl.exception.InternalSessionException;
import edu.bsu.registrator.storage.GlobalStorage;

import java.util.*;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class SessionImpl implements Session {

  private final Map<AbstractModel, PersistentModelWrapper> persistentMap = new HashMap<AbstractModel, PersistentModelWrapper>();

  public SessionImpl() {
  }

  private void putToPersistentMap(AbstractModel model, PersistentModelWrapper wrapper) {
    PersistentModelWrapper persistedWrapper = persistentMap.get(model);
    if (persistedWrapper == null) {
      persistentMap.put(model, wrapper);
    } else {
      persistedWrapper.setOperation(wrapper.getOperation());
    }
  }

  @Override
  public <T extends AbstractModel> void saveModel(T model) {
    final T copy = PersistentModelOperations.getObjectCopy(model);
    Operation operation;
    if (model.getId() == null) {
      operation = Operation.SAVE;
    } else {
      operation = Operation.UPDATE;
    }
    putToPersistentMap(model, new PersistentModelWrapper(copy, operation));
  }

  @Override
  public <T extends AbstractModel> void deleteModel(T model) {
    if (model.getId() == null) {
      throw new InternalSessionException("Object id cannot be null.");
    }
    final T copy = PersistentModelOperations.getObjectCopy(model);
    putToPersistentMap(model, new PersistentModelWrapper(copy, Operation.DELETE));
  }

  @Override
  public <T extends AbstractModel> List<T> loadAllModelsOfType(Class<T> type) {
    final List<T> storageModels = GlobalStorage.getAllModelsByType(type);
    final List<T> resultModels = new ArrayList<T>(storageModels.size());
    T copy;
    for (T model : storageModels) {
      copy = PersistentModelOperations.getObjectCopy(model);
      putToPersistentMap(copy, new PersistentModelWrapper(model, Operation.LOAD));
      resultModels.add(copy);
    }
    return resultModels;
  }

  @Override
  public <T extends AbstractModel> T loadModelById(Long id, Class<T> type) {
    final T storageModel = GlobalStorage.getModelFromStorage(id, type);
    final T copy = PersistentModelOperations.getObjectCopy(storageModel);
    putToPersistentMap(copy, new PersistentModelWrapper(storageModel, Operation.LOAD));
    return copy;
  }

  @Override
  public void commit() {
    try {
      AbstractModel model;
      PersistentModelWrapper wrapper;
      for (Map.Entry<AbstractModel, PersistentModelWrapper> entry : persistentMap.entrySet()) {
        model = entry.getKey();
        wrapper = entry.getValue();
        switch (wrapper.getOperation()) {
        case LOAD:
          if (PersistentModelOperations.isModelChanged(model, wrapper.getPersistentModel())) {
            GlobalStorage.updateModelInStorage(PersistentModelOperations.getObjectCopy(model));
          }
          break;
        case SAVE:
          GlobalStorage.insertModelToStorage(PersistentModelOperations.getObjectCopy(model));
          break;
        case UPDATE:
          if (PersistentModelOperations.isModelChanged(model, wrapper.getPersistentModel())) {
            GlobalStorage.updateModelInStorage(PersistentModelOperations.getObjectCopy(model));
          }
          break;
        case DELETE:
          GlobalStorage.removeModelFromStorage(model);
          break;
        }
      }
    } catch (Exception e) {
      rollback();
      throw new InternalSessionException(e.getMessage());
    }
    persistentMap.clear();
  }

  @Override
  public void rollback() {
    AbstractModel model;
    PersistentModelWrapper wrapper;
    for (Map.Entry<AbstractModel, PersistentModelWrapper> entry : persistentMap.entrySet()) {
      model = entry.getKey();
      wrapper = entry.getValue();
      PersistentModelOperations.copyObjectState(wrapper.getPersistentModel(), model);
    }
    persistentMap.clear();
  }
}
