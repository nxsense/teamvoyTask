package store.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO extends AbstractDTO{
    private int id;
    private int userId;
    private List<Integer> goodsId;
}
