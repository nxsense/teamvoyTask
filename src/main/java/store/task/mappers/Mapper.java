package store.task.mappers;

import store.task.dto.AbstractDTO;
import store.task.entities.AbstractEntity;

public abstract class Mapper<E extends AbstractEntity, D extends AbstractDTO> {

    public abstract E toEntity(D dto);

    public abstract D toDto(E entity);
}