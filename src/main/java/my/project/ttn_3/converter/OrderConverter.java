package my.project.ttn_3.converter;

import my.project.ttn_3.DTO.orders.OrderRequest;
import my.project.ttn_3.DTO.orders.OrderResponse;
import my.project.ttn_3.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Data
@AllArgsConstructor
public class OrderConverter {
    public Orders toOrder(OrderRequest orderRequest) {
        Orders newOrder = new Orders();
        newOrder.setOrders(orderRequest.getOrders());
        newOrder.setDateCreate(LocalDate.now());
        return newOrder;
    }

    public OrderResponse toResponse(Orders orders){
        return new OrderResponse(orders.getId(), orders.getOrders(), orders.getDateCreate());
    }

}
