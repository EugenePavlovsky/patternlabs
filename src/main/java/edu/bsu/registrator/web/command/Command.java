package edu.bsu.registrator.web.command;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public interface Command {

  void execute();

  CommandParameter getParameter();

  void setParameter(CommandParameter parameter);
}
