package edu.bsu.registrator.web.helper.handler;

import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.command.CommandParameter;
import edu.bsu.registrator.web.helper.CommandRetriever;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class HomePageHandler {

  public static void processHomePage(HttpServletRequest request, HttpServletResponse response) {
    processHomePage(new CommandParameter(request, response));
  }

  public static void processHomePage(CommandParameter parameter) {
    Command command = CommandRetriever.getHomePageCommand();
    command.setParameter(parameter);
    command.execute();
  }
}
