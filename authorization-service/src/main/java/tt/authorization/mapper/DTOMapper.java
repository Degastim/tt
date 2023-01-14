package tt.authorization.mapper;

import tt.authorization.entity.AbstractEntity;

public interface DTOMapper<T extends AbstractEntity, R> {
    T toEntity(R dto);

    R toDTO(T entity);
}
