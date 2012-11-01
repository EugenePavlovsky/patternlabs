package edu.bsu.registrator.dto.impl;

import edu.bsu.registrator.dto.DTO;

/**
 * @author Eugene Pavlovsky
 * @since 31.10.12
 */
public class IdentifierDTO implements DTO {

  private Long id;

  public IdentifierDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
