package edu.bsu.registrator.web.customfilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public interface ICustomFilter {

  void doFilter(ServletRequest req, ServletResponse resp);

  void init();

  void destroy();

  ICustomFilter getInnerFilter();

  void setInnerFilter(ICustomFilter innerFilter);
}
