package edu.bsu.registrator.web.helper.handler;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.command.CommandParameter;
import edu.bsu.registrator.web.helper.CommandRetriever;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class ErrorHandler {

  public static void processError(HttpServletRequest request, HttpServletResponse response) {
    processError(new CommandParameter(request, response));
  }

  public static void processError(CommandParameter parameter) {
    Command errorCommand = CommandRetriever.getErrorCommand();
    errorCommand.setParameter(parameter);
    errorCommand.execute();
  }

  public static boolean isErrorFlag(HttpSession session) {
    return session.getAttribute(RegistratorSettings.getSettings().getErrorFlagKey()) != null;
  }

  public static void removeErrorFlag(HttpSession session) {
    session.removeAttribute(RegistratorSettings.getSettings().getErrorFlagKey());
  }
}
