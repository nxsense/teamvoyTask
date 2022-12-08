package store.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO extends AbstractDTO{
    private int userId;
    private boolean completed;
    private boolean paid;
    private List<Integer> goodsId;
    private LocalDateTime creatingTime;
}
