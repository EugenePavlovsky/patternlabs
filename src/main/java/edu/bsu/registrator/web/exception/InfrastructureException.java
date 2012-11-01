package edu.bsu.registrator.web.exception;

/**
 * @author Eugene Pavlovsky
 * @since 24.10.12
 */
public class InfrastructureException extends RuntimeException {

  public InfrastructureException() {
  }

  public InfrastructureException(String message) {
    super(message);
  }

  public InfrastructureException(String message, Throwable cause) {
    super(message, cause);
  }

  public InfrastructureException(Throwable cause) {
    super(cause);
  }
}
