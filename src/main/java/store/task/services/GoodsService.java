package store.task.services;

import store.task.dto.GoodsDTO;

import java.util.List;

public interface GoodsService {
    GoodsDTO save(GoodsDTO goodsDTO);
    GoodsDTO findById(int id);
    List<GoodsDTO> findAllGoods();
}
