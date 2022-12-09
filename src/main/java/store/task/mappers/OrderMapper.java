package store.task.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import store.task.dto.OrderDTO;
import store.task.entities.Good;
import store.task.entities.Order;
import store.task.repositories.GoodsRepository;
import store.task.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OrderMapper extends AbstractMapper<Order, OrderDTO>{

    private final ModelMapper mapper;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;
    @Autowired
    OrderMapper(ModelMapper mapper, GoodsRepository goodsRepository, UserRepository userRepository) {
        super(Order.class, OrderDTO.class);
        this.mapper = mapper;
        this.goodsRepository = goodsRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Order.class, OrderDTO.class)
                .addMappings(m -> m.skip(OrderDTO::setUserId))
                .addMappings(m -> m.skip(OrderDTO::setGoodsId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(OrderDTO.class, Order.class)
                .addMappings(m -> m.skip(Order::setUser))
                .addMappings(m -> m.skip(Order::setGoods)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Order source, OrderDTO destination) {
        destination.setUserId(getId(source));
        destination.setGoodsId(getIds(source));

    }

    private List<Integer> getIds(Order source){
        List<Integer> ids = new ArrayList<>();
        for (Good good : source.getGoods()) {
            ids.add(good.getId());
        }
        return ids;
    }
    private int getId(Order source) {
        return Objects.isNull(source) ? null : source.getUser().getId();
    }

    @Override
    void mapSpecificFields(OrderDTO source, Order destination) {
        destination.setUser(userRepository.findById(source.getUserId()).orElse(null));
        destination.setGoods(goodsRepository.findAll());
    }

}
