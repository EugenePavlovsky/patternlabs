package edu.bsu.registrator.web.helper;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.command.CommandParameter;
import edu.bsu.registrator.web.command.impl.*;
import edu.bsu.registrator.web.exception.InfrastructureException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class CommandRetriever {

  private static final Map<String, Class<? extends Command>> VIEW_COMMAND_MAP = new HashMap<String, Class<? extends Command>>();

  static {
    VIEW_COMMAND_MAP.put("error", ErrorCommand.class);

    VIEW_COMMAND_MAP.put("index", IndexCommand.class);
    VIEW_COMMAND_MAP.put("", IndexCommand.class);

    VIEW_COMMAND_MAP.put("new_contact", CreateNewContactCommand.class);
    VIEW_COMMAND_MAP.put("new_user", CreateNewUserCommand.class);
    VIEW_COMMAND_MAP.put("save_new_contact", SaveNewContactCommand.class);
    VIEW_COMMAND_MAP.put("save_new_user", SaveNewUserCommand.class);
    VIEW_COMMAND_MAP.put("view_contacts", ViewContactsCommand.class);
    VIEW_COMMAND_MAP.put("view_users", ViewUsersCommand.class);
    VIEW_COMMAND_MAP.put("view_contact", ViewContactCommand.class);
    VIEW_COMMAND_MAP.put("view_user", ViewUserCommand.class);
    VIEW_COMMAND_MAP.put("edit_contact", EditContactCommand.class);
    VIEW_COMMAND_MAP.put("edit_user", EditUserCommand.class);
    VIEW_COMMAND_MAP.put("delete_contact", DeleteContactCommand.class);
    VIEW_COMMAND_MAP.put("delete_user", DeleteUserCommand.class);
    VIEW_COMMAND_MAP.put("save_contact", SaveContactCommand.class);
    VIEW_COMMAND_MAP.put("save_user", SaveUserCommand.class);
  }

  public static Command retrieveCommand(HttpServletRequest request, HttpServletResponse response) {
    try {
      Class<? extends Command> commandClass = VIEW_COMMAND_MAP.get(getViewId(request));
      if (commandClass == null) {
        throw new InfrastructureException("Could not find command to handle request.");
      }
      Command command = commandClass.newInstance();
      command.setParameter(new CommandParameter(request, response));
      return command;
    } catch (Exception e) {
      throw new InfrastructureException(e.getMessage());
    }
  }

  public static Command getErrorCommand() {
    return new ErrorCommand();
  }

  public static Command getHomePageCommand() {
    return new IndexCommand();
  }

  @SuppressWarnings("unchecked")
  public static Command getCommandByClassName(String className) {
    Command command = null;
    if (className != null) {
      try {
        Class<? extends Command> commandClass = (Class<? extends Command>) Class.forName(className);
        command = commandClass.newInstance();
      } catch (Exception e) {
        throw new InfrastructureException(e.getMessage());
      }
    }
    return command;
  }

  private static String getViewId(HttpServletRequest request) {
    String viewId = request.getParameter("actionName");
    if (viewId == null) {
      viewId = request.getRequestURI().substring(request.getContextPath().length());
      if (viewId.startsWith(RegistratorSettings.getSettings().getRootUrl())) {
        viewId = viewId.substring(RegistratorSettings.getSettings().getRootUrl().length(), viewId.length());
      }
      if (viewId.startsWith("/")) {
        viewId = viewId.substring(1, viewId.length());
      }
      if (viewId.endsWith("/")) {
        viewId = viewId.substring(0, viewId.length() - 1);
      }
    }
    return viewId;
  }
}
