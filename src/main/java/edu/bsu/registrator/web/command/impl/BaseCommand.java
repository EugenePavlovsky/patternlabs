package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.web.command.Command;
import edu.bsu.registrator.web.command.CommandParameter;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public abstract class BaseCommand implements Command {

  protected CommandParameter parameter;

  public BaseCommand() {
  }

  public CommandParameter getParameter() {
    return parameter;
  }

  public void setParameter(CommandParameter parameter) {
    this.parameter = parameter;
  }
}
