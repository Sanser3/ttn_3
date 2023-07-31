package my.project.ttn_3.service;

import my.project.ttn_3.DTO.orders.OrderRequest;
import my.project.ttn_3.DTO.orders.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAllOrderRequest();

}
