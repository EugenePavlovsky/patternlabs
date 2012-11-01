package edu.bsu.registrator.session.impl.exception;

/**
 * @author Eugene Pavlovsky
 * @since 25.10.12
 */
public class InternalSessionException extends RuntimeException{

  public InternalSessionException() {
  }

  public InternalSessionException(String message) {
    super(message);
  }

  public InternalSessionException(String message, Throwable cause) {
    super(message, cause);
  }

  public InternalSessionException(Throwable cause) {
    super(cause);
  }
}
