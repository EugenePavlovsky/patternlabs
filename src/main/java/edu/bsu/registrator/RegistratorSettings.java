package edu.bsu.registrator;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class RegistratorSettings {

  private static final RegistratorSettings REGISTRATOR_SETTINGS = new RegistratorSettings();

  private String contextPath;
  private String rootUrl;
  private String syncTokenKey;
  private String errorFlagKey;

  private RegistratorSettings() {
  }

  public static RegistratorSettings getSettings() {
    return REGISTRATOR_SETTINGS;
  }

  public String getContextPath() {
    return contextPath;
  }

  public void setContextPath(String contextPath) {
    this.contextPath = contextPath;
  }

  public String getRootUrl() {
    return rootUrl;
  }

  public void setRootUrl(String rootUrl) {
    this.rootUrl = rootUrl;
  }

  public String getSyncTokenKey() {
    return syncTokenKey;
  }

  public void setSyncTokenKey(String syncTokenKey) {
    this.syncTokenKey = syncTokenKey;
  }

  public String getErrorFlagKey() {
    return errorFlagKey;
  }

  public void setErrorFlagKey(String errorFlagKey) {
    this.errorFlagKey = errorFlagKey;
  }
}
