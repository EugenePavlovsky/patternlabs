package edu.bsu.registrator.storage.exception;

/**
 * @author Eugene Pavlovsky
 * @since 14.10.12
 */
public class NonUniqueModelException extends RuntimeException {

  public NonUniqueModelException() {
  }

  public NonUniqueModelException(String message) {
    super(message);
  }

  public NonUniqueModelException(String message, Throwable cause) {
    super(message, cause);
  }

  public NonUniqueModelException(Throwable cause) {
    super(cause);
  }
}
