package edu.bsu.registrator.storage;

import edu.bsu.registrator.storage.exception.ModelNotFoundException;
import edu.bsu.registrator.storage.exception.NonUniqueModelException;
import edu.bsu.registrator.model.AbstractModel;

import java.util.*;

/**
 * @author Eugene Pavlovsky
 * @since 14.10.12
 */
public class GlobalStorage {

  private static final Map<Class<? extends AbstractModel>, Map<Long, ? extends AbstractModel>> CLASS_MODEL_MAP =
      new HashMap<Class<? extends AbstractModel>, Map<Long, ? extends AbstractModel>>();

  private static final Map<Class<? extends AbstractModel>, Long> CLASS_ID_SEQUENCE_MAP = new HashMap<Class<? extends AbstractModel>, Long>();

  @SuppressWarnings("unchecked")
  public synchronized static <T extends AbstractModel> void insertModelToStorage(T model) {
    final Class<T> modelClass = (Class<T>) model.getClass();

    Map<Long, T> modelMap = (Map<Long, T>) CLASS_MODEL_MAP.get(modelClass);

    if (modelMap == null) {
      modelMap = new HashMap<Long, T>();
      CLASS_MODEL_MAP.put(modelClass, modelMap);
    }

    if (model.getId() == null) {
      model.setId(getNextId(modelClass));
    } else if (modelMap.containsKey(model.getId())) {
      throw new NonUniqueModelException("Model with id = " + model.getId() + " is already stored.");
    }

    modelMap.put(model.getId(), model);
    Long sequenceId = CLASS_ID_SEQUENCE_MAP.get(modelClass);
    if (sequenceId == null || model.getId().compareTo(sequenceId) != -1) {
      sequenceId = model.getId() + 1;
    }
    CLASS_ID_SEQUENCE_MAP.put(modelClass, sequenceId);
  }

  public synchronized static <T extends AbstractModel> void updateModelInStorage(T model) {
    if (model.getId() == null) {
      throw new ModelNotFoundException("Model id cannot be null.");
    }

    @SuppressWarnings("unchecked")
    Map<Long, T> modelMap = (Map<Long, T>) CLASS_MODEL_MAP.get(model.getClass());

    if (modelMap == null || !modelMap.containsKey(model.getId())) {
      throw new ModelNotFoundException("Model with id = " + model.getId() + " not found.");
    }

    modelMap.put(model.getId(), model);
  }

  public synchronized static <T extends AbstractModel> T getModelFromStorage(Long id, Class<T> type) {
    @SuppressWarnings("unchecked")
    final Map<Long, T> modelMap = (Map<Long, T>) CLASS_MODEL_MAP.get(type);

    T model;
    if (modelMap == null || (model = modelMap.get(id)) == null) {
      throw new ModelNotFoundException("Model with id = " + id + " not found.");
    }
    return model;
  }

  public synchronized static <T extends AbstractModel> List<T> getAllModelsByType(Class<T> type) {
    @SuppressWarnings("unchecked")
    final Map<Long, T> modelMap = (Map<Long, T>) CLASS_MODEL_MAP.get(type);

    if (modelMap != null) {
      return new ArrayList<T>(modelMap.values());
    } else {
      return new ArrayList<T>();
    }
  }

  public synchronized static <T extends AbstractModel> void removeModelFromStorage(Long id, Class<T> type) {
    @SuppressWarnings("unchecked")
    final Map<Long, T> modelMap = (Map<Long, T>) CLASS_MODEL_MAP.get(type);

    if (modelMap == null || modelMap.remove(id) == null) {
      throw new ModelNotFoundException("Model with id = " + id + " not found.");
    }
  }

  public synchronized static <T extends AbstractModel> void removeModelFromStorage(T model) {
    removeModelFromStorage(model.getId(), model.getClass());
  }

  public synchronized static <T extends AbstractModel> Long getNextId(Class<T> type) {
    Long id = CLASS_ID_SEQUENCE_MAP.get(type);
    if (id == null) {
      id = 1L;
    }
    CLASS_ID_SEQUENCE_MAP.put(type, id + 1);
    return id;
  }
}
