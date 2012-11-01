package edu.bsu.registrator.dto.impl;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class SynchronizationTokenDTO extends ContextDTO {

  private String synchronizationToken;

  public SynchronizationTokenDTO() {
  }

  public String getSynchronizationToken() {
    return synchronizationToken;
  }

  public void setSynchronizationToken(String synchronizationToken) {
    this.synchronizationToken = synchronizationToken;
  }
}
