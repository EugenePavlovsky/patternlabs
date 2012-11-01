package edu.bsu.registrator.dto.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.dto.DTO;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class ContextDTO implements DTO {

  private String context = RegistratorSettings.getSettings().getContextPath();
  private String rootUrl = RegistratorSettings.getSettings().getRootUrl();

  public ContextDTO() {
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getRootUrl() {
    return rootUrl;
  }

  public void setRootUrl(String rootUrl) {
    this.rootUrl = rootUrl;
  }
}
