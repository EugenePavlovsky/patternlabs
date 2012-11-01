package edu.bsu.registrator.session.impl;

import edu.bsu.registrator.model.AbstractModel;

/**
 * @author Eugene Pavlovsky
 * @since 25.10.12
 */
public class PersistentModelWrapper {

  private AbstractModel persistentModel;
  private Operation operation;

  public PersistentModelWrapper(AbstractModel persistentModel, Operation operation) {
    this.persistentModel = persistentModel;
    this.operation = operation;
  }

  public AbstractModel getPersistentModel() {
    return persistentModel;
  }

  public void setPersistentModel(AbstractModel persistentModel) {
    this.persistentModel = persistentModel;
  }

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }
}
