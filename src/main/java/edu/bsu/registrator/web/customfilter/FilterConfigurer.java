package edu.bsu.registrator.web.customfilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eugene Pavlovsky
 * @since 17.09.12
 */
public class FilterConfigurer {

  public static ICustomFilter configureFilters(InputStream inputStream) {
    final List<String> classNames = new ArrayList<String>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    try {
      String classNameLine;
      while ((classNameLine = reader.readLine()) != null) {
        classNameLine = classNameLine.trim();
        if (!classNameLine.isEmpty()) {
          classNames.add(classNameLine);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    final List<ICustomFilter> filters = new ArrayList<ICustomFilter>(classNames.size());
    Class tmpClass;
    Object filterInstance;
    for (String className : classNames) {
      try {
        tmpClass = Class.forName(className);
        filterInstance = tmpClass.newInstance();
        if (filterInstance instanceof ICustomFilter) {
          filters.add((ICustomFilter) filterInstance);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    ICustomFilter outerFilter = null;
    for (ICustomFilter filter : filters) {
      if (outerFilter != null) {
        outerFilter.setInnerFilter(filter);
      }
      outerFilter = filter;
    }
    return filters.isEmpty() ? null : filters.get(0);
  }
}
