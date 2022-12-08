package store.task.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.task.dto.GoodsDTO;
import store.task.entities.Good;

@Component
public class GoodsMapper extends AbstractMapper<Good, GoodsDTO>{
     @Autowired
    GoodsMapper(Class<Good> entityClass, Class<GoodsDTO> dtoClass) {
        super(entityClass, dtoClass);
    }
}
