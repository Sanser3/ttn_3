package my.project.ttn_3.service.impl;

import my.project.ttn_3.DTO.orders.OrderRequest;
import my.project.ttn_3.DTO.orders.OrderResponse;
import my.project.ttn_3.converter.OrderConverter;
import my.project.ttn_3.entity.Orders;
import my.project.ttn_3.repository.OrderRepository;
import my.project.ttn_3.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;

    public OrderResponse createOrder(OrderRequest orderRequest){
        Orders newOrder = orderConverter.toOrder(orderRequest);
        newOrder = orderRepository.save(newOrder);
        return new OrderResponse(newOrder.getId(), newOrder.getOrders(), newOrder.getDateCreate());
    }

    public List<OrderResponse> findAllOrderRequest(){
        List<Orders> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderConverter::toResponse)
                .collect(Collectors.toList());
    }
}
