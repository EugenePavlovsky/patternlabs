package edu.bsu.registrator.resource;

import java.io.InputStream;

/**
 * @author Eugene Pavlovsky
 * @since 24.10.12
 */
public class ResourceManager {

  public static InputStream getResourceAsStream(String pathToResource){
    return ResourceManager.class.getResourceAsStream(pathToResource);
  }
}
