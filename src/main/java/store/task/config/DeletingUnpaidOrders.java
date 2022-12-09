package store.task.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import store.task.dto.OrderDTO;
import store.task.services.OrdersService;

import java.util.List;

@EnableScheduling
@Configuration
public class DeletingUnpaidOrders {
    private static final Long ALLOWED_TIME = (long) (60 * 10);
    private final OrdersService ordersService;

    public DeletingUnpaidOrders(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Async
    @Scheduled(fixedRate = 1000)
    public void checkOrders() {
        List<OrderDTO> orders = ordersService.findAllOrders();
        for (OrderDTO order:orders) {
            if((System.currentTimeMillis() - order.getCreatingTime()) > ALLOWED_TIME  && !order.isPaid()){
                ordersService.delete(order);
            }
        }
    }
}
