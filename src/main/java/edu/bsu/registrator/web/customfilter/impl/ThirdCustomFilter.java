package edu.bsu.registrator.web.customfilter.impl;

import edu.bsu.registrator.web.customfilter.CustomFilter;
import edu.bsu.registrator.web.customfilter.ICustomFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public class ThirdCustomFilter extends CustomFilter {

  public ThirdCustomFilter() {
  }

  public ThirdCustomFilter(ICustomFilter innerFilter) {
    super(innerFilter);
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp) {
    System.out.println("Processing customfilter #3");
    if (getInnerFilter() != null) {
      getInnerFilter().doFilter(req, resp);
    }
  }

  @Override
  public void init() {
    System.out.println("Initializing customfilter #3");
    if (getInnerFilter() != null) {
      getInnerFilter().init();
    }
  }

  @Override
  public void destroy() {
    System.out.println("Disposing customfilter #3");
    if (getInnerFilter() != null) {
      getInnerFilter().destroy();
    }
  }
}
