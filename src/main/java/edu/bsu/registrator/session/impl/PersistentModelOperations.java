package edu.bsu.registrator.session.impl;

import edu.bsu.registrator.model.AbstractModel;
import edu.bsu.registrator.session.impl.exception.InternalSessionException;

import java.lang.reflect.Field;

/**
 * @author Eugene Pavlovsky
 * @since 25.10.12
 */
public class PersistentModelOperations {

  public static <T extends AbstractModel> void copyObjectState(T source, T target) {
    if (!source.getClass().equals(target.getClass())) {
      throw new InternalSessionException("Source and target types does not match.");
    }
    try {
      boolean accessible;
      for (Field field : source.getClass().getDeclaredFields()) {
        accessible = field.isAccessible();
        field.setAccessible(true);
        field.set(target, field.get(source));
        field.setAccessible(accessible);
      }
    } catch (Exception e) {
      throw new InternalSessionException(e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends AbstractModel> T getObjectCopy(T prototype) {
    try {
      return (T) prototype.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalSessionException(e.getMessage());
    }
  }

  public static <T extends AbstractModel> boolean isModelChanged(T modelState1, T modelState2) {
    if (!modelState1.getClass().equals(modelState2.getClass())) {
      throw new InternalSessionException("Source and target types does not match.");
    }
    try {
      boolean accessible;
      Object value1;
      Object value2;
      for (Field field : modelState1.getClass().getDeclaredFields()) {
        accessible = field.isAccessible();
        field.setAccessible(true);
        value1 = field.get(modelState1);
        value2 = field.get(modelState2);
        field.setAccessible(accessible);
        if (value1 == null && value2 == null) {
          continue;
        }
        if (value1 == null || value2 == null || !value1.equals(value2)) {
          return true;
        }
      }
    } catch (Exception e) {
      throw new InternalSessionException(e.getMessage());
    }
    return false;
  }
}
