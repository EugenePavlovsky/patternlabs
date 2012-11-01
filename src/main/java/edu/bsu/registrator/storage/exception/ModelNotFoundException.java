package edu.bsu.registrator.storage.exception;

import edu.bsu.registrator.web.exception.InfrastructureException;

/**
 * @author Eugene Pavlovsky
 * @since 14.10.12
 */
public class ModelNotFoundException extends InfrastructureException {

  public ModelNotFoundException() {
  }

  public ModelNotFoundException(String message) {
    super(message);
  }

  public ModelNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public ModelNotFoundException(Throwable cause) {
    super(cause);
  }
}
