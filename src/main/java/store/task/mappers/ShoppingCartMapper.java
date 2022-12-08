package store.task.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.task.dto.OrderDTO;
import store.task.dto.ShoppingCartDTO;
import store.task.entities.Good;
import store.task.entities.Order;
import store.task.entities.ShoppingCart;
import store.task.repositories.GoodsRepository;
import store.task.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ShoppingCartMapper extends AbstractMapper<ShoppingCart, ShoppingCartDTO>{

    private final ModelMapper mapper;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;
    @Autowired
    ShoppingCartMapper(Class<ShoppingCart> entityClass, Class<ShoppingCartDTO> dtoClass, ModelMapper mapper, GoodsRepository goodsRepository, UserRepository userRepository) {
        super(entityClass, dtoClass);
        this.mapper = mapper;
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ShoppingCart.class, ShoppingCartDTO.class)
                .addMappings(m -> m.skip(ShoppingCartDTO::setUserId))
                .addMappings(m -> m.skip(ShoppingCartDTO::setGoodsId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ShoppingCartDTO.class, ShoppingCart.class)
                .addMappings(m -> m.skip(ShoppingCart::setUser))
                .addMappings(m -> m.skip(ShoppingCart::setGoods)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ShoppingCart source, ShoppingCartDTO destination) {
        destination.setUserId(getId(source));
        destination.setGoodsId(getIds(source));

    }

    private List<Integer> getIds(ShoppingCart source){
        List<Integer> ids = new ArrayList<>();
        for (Good good : source.getGoods()) {
            ids.add(good.getId());
        }
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : ids;
    }
    private int getId(ShoppingCart source) {
        return Objects.isNull(source) ? null : source.getUser().getId();
    }

    @Override
    void mapSpecificFields(ShoppingCartDTO source, ShoppingCart destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination.setGoods(goodsRepository.findAll());
    }

}
