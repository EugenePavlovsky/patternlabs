package edu.bsu.registrator.web.customfilter;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public abstract class CustomFilter implements ICustomFilter {

  private ICustomFilter innerFilter;

  public CustomFilter() {
  }

  public CustomFilter(ICustomFilter innerFilter) {
    this.innerFilter = innerFilter;
  }

  public ICustomFilter getInnerFilter() {
    return innerFilter;
  }

  public void setInnerFilter(ICustomFilter innerFilter) {
    this.innerFilter = innerFilter;
  }
}
