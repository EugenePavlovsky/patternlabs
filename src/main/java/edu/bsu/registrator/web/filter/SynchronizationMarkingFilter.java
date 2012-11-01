package edu.bsu.registrator.web.filter;

import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.command.CommandParameter;
import edu.bsu.registrator.web.helper.CommandRetriever;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public class SynchronizationMarkingFilter implements Filter {

  private static final String COMMAND_CLASS_PARAM_KEY = "COMMAND";

  private Command command;

  @Override
  public void init(FilterConfig config) throws ServletException {
    command = CommandRetriever.getCommandByClassName(config.getInitParameter(COMMAND_CLASS_PARAM_KEY));
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws ServletException, IOException {
    if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
      if (command != null) {
        command.setParameter(new CommandParameter((HttpServletRequest) req, (HttpServletResponse) resp));
        command.execute();
      }
    }
    chain.doFilter(req, resp);
  }

  @Override
  public void destroy() {
  }

}
