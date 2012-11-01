package edu.bsu.registrator.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Eugene Pavlovsky
 * @since 22.10.12
 */
public class CommandParameter {

  private HttpServletRequest request;
  private HttpServletResponse response;

  public CommandParameter(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }

  public OutputStream getOutputStream() throws IOException {
    return response.getOutputStream();
  }

  public Object getAttributeFromSession(String name) {
    return request.getSession().getAttribute(name);
  }

  public void setAttributeToSession(String name, Object value) {
    request.getSession().setAttribute(name, value);
  }

  public String getContextPath() {
    return request.getContextPath();
  }

  public String getParameter(String name) {
    return request.getParameter(name);
  }

  public void invalidateSession() {
    request.getSession().invalidate();
  }
}
