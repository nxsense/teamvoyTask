package store.task.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.task.dto.GoodsDTO;
import store.task.mappers.GoodsMapper;
import store.task.repositories.GoodsRepository;
import store.task.services.GoodsService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodServiceImpl implements GoodsService{
    private final GoodsRepository repository;
    private final GoodsMapper mapper;

    @Autowired
    public GoodServiceImpl(GoodsRepository repository, GoodsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GoodsDTO save(GoodsDTO goodsDTO) {
        return mapper.toDto(repository.save(mapper.toEntity(goodsDTO)));
    }

    @Override
    public GoodsDTO findById(int id) {
        return mapper.toDto(repository.getReferenceById(id));
    }

    @Override
    public List<GoodsDTO> findAllGoods() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
