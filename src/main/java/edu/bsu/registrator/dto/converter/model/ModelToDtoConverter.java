package edu.bsu.registrator.dto.converter.model;

import edu.bsu.registrator.dto.DTO;
import edu.bsu.registrator.model.AbstractModel;

/**
 * @author Eugene Pavlovsky
 * @since 15.10.12
 */
public interface ModelToDtoConverter<T extends AbstractModel, V extends DTO> {

  V convertToDTO(T model);
}
