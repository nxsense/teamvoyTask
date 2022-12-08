package store.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "goods")
public class Good extends AbstractEntity{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "availiable", nullable = false)
    private boolean availiable;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Good)) return false;
        if (!super.equals(o)) return false;
        Good good = (Good) o;
        return getId() == good.getId() && Double.compare(good.getPrice(), getPrice()) == 0 && isAvailiable() == good.isAvailiable() && getName().equals(good.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getPrice(), isAvailiable());
    }
}
