package edu.bsu.registrator.web.command.impl;

import edu.bsu.registrator.RegistratorSettings;
import edu.bsu.registrator.util.SynchronizedTokenUtil;

/**
 * @author Eugene Pavlovsky
 * @since 29.10.12
 */
public class SynchronizationCommand extends BaseCommand {

  @Override
  public void execute() {
    final String synchToken = (String) getParameter().getAttributeFromSession(RegistratorSettings.getSettings().getSyncTokenKey());
    if (synchToken != null) {
      String actualSynchToken = getParameter().getParameter(RegistratorSettings.getSettings().getSyncTokenKey());
      if (actualSynchToken == null || !synchToken.equals(actualSynchToken)) {
        getParameter().setAttributeToSession(RegistratorSettings.getSettings().getErrorFlagKey(), 1);
      }
    }
    getParameter().setAttributeToSession(RegistratorSettings.getSettings().getSyncTokenKey(), SynchronizedTokenUtil.generateToken());
  }


}
