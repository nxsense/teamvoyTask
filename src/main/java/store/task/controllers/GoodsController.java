package store.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.task.dto.GoodsDTO;
import store.task.services.GoodsService;

import java.util.List;

@RestController
@RequestMapping(name = "/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping()
    public GoodsDTO addGood(@RequestBody GoodsDTO goodsDTO){
        return goodsService.save(goodsDTO);
    }

    @GetMapping
    public List<GoodsDTO> getAllGoods(){
        return goodsService.findAllGoods();
    }
}
