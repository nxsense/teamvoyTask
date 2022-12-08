package store.task.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Table(name = "shopping_cart")
public class ShoppingCart extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shopping_cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Good> goods;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShoppingCart)) return false;
        if (!super.equals(o)) return false;
        ShoppingCart that = (ShoppingCart) o;
        return getId() == that.getId() && getUser().equals(that.getUser()) && getGoods().equals(that.getGoods());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUser(), getGoods());
    }
}
