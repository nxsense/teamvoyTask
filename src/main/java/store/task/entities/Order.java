package store.task.entities;


import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "orders")

public class Order extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "paid")
    private boolean paid;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "orderitems",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "itemId"))
    @ToString.Exclude
    private List<Good> goods;

    @Column(name = "creatingTime")
    private Long creatingTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && isCompleted() == order.isCompleted() && isPaid() == order.isPaid() && getUser().equals(order.getUser()) && getGoods().equals(order.getGoods()) && getCreatingTime().equals(order.getCreatingTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getUser(), isCompleted(), isPaid(), getGoods(), getCreatingTime());
    }
}
