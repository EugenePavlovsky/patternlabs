package edu.bsu.registrator.model;

/**
 * @author Eugene Pavlovsky
 * @since 14.10.12
 */
public class AbstractModel implements Cloneable{

  private Long id;

  public AbstractModel() {
  }

  public AbstractModel(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
