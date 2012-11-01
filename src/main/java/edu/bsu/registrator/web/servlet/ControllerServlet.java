package edu.bsu.registrator.web.servlet;

import edu.bsu.registrator.web.exception.InfrastructureException;
import edu.bsu.registrator.web.helper.CommandRetriever;
import edu.bsu.registrator.web.helper.handler.ErrorHandler;
import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.customfilter.FilterConfigurer;
import edu.bsu.registrator.web.customfilter.ICustomFilter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public class ControllerServlet extends HttpServlet {

  private static final String FILTER_CONFIG_PARAM_KEY = "FILTER_CONFIG_FILE";

  private ICustomFilter rootCustomFilter;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    String filterConfigFileName = config.getInitParameter(FILTER_CONFIG_PARAM_KEY);
    InputStream configInputStream = config.getServletContext().getResourceAsStream(filterConfigFileName);
    if (configInputStream != null) {
      rootCustomFilter = FilterConfigurer.configureFilters(configInputStream);
      if (rootCustomFilter != null) {
        rootCustomFilter.init();
      }
    } else {
      System.err.println("Cannot find filter configuration file.");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processRequest(request, response);
  }

  @Override
  public void destroy() {
    super.destroy();
    if (rootCustomFilter != null) {
      rootCustomFilter.destroy();
    }
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) {
    if (rootCustomFilter != null) {
      rootCustomFilter.doFilter(request, response);
    }
    if (ErrorHandler.isErrorFlag(request.getSession())) {
      ErrorHandler.removeErrorFlag(request.getSession());
      ErrorHandler.processError(request, response);
      return;
    }
    try {
      Command command = CommandRetriever.retrieveCommand(request, response);
      command.execute();
    } catch (InfrastructureException e) {
      ErrorHandler.processError(request, response);
    }
  }
}
