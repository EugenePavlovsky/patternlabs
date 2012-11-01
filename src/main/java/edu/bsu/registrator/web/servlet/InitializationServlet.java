package edu.bsu.registrator.web.servlet;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.helper.CommandRetriever;
import edu.bsu.registrator.web.helper.handler.ErrorHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Eugene Pavlovsky
 * @since 15.10.12
 */
public class InitializationServlet extends HttpServlet {

  private static final String COMMAND_CLASS_PARAM_KEY = "COMMAND";
  private static final String ROOT_URL_PARAM_KEY = "ROOT_URL";
  private static final String SYNCH_TOKEN_KEY_PARAM_KEY = "SYNCH_TOKEN_KEY";
  private static final String ERROR_FLAG_KEY_PARAM_KEY = "ERROR_FLAG_KEY";

  @Override
  public void init() throws ServletException {
    super.init();
    RegistratorSettings.getSettings().setContextPath(getServletContext().getContextPath());
    RegistratorSettings.getSettings().setRootUrl(getServletConfig().getInitParameter(ROOT_URL_PARAM_KEY));
    RegistratorSettings.getSettings().setSyncTokenKey(getServletConfig().getInitParameter(SYNCH_TOKEN_KEY_PARAM_KEY));
    RegistratorSettings.getSettings().setErrorFlagKey(getServletConfig().getInitParameter(ERROR_FLAG_KEY_PARAM_KEY));
    Command command = CommandRetriever.getCommandByClassName(getServletConfig().getInitParameter(COMMAND_CLASS_PARAM_KEY));
    if (command != null) {
      command.execute();
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ErrorHandler.processError(request, response);
  }
}
