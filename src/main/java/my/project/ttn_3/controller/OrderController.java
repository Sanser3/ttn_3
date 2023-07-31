package my.project.ttn_3.controller;

import my.project.ttn_3.DTO.orders.OrderRequest;
import my.project.ttn_3.DTO.orders.OrderResponse;
import my.project.ttn_3.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        List<OrderResponse> response = orderService.findAllOrderRequest();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addNewOrder(@RequestBody OrderRequest request){
        OrderResponse response = orderService.createOrder(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
