package store.task.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class AbstractDTO implements Serializable {
    private int id;
}
