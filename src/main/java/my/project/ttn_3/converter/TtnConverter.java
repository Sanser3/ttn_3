package my.project.ttn_3.converter;

import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.DTO.orders.OrderRequest;
import my.project.ttn_3.DTO.orders.OrderResponse;
import my.project.ttn_3.DTO.ttns.TtnRequest;
import my.project.ttn_3.DTO.ttns.TtnResponse;
import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.entity.Orders;
import my.project.ttn_3.entity.Ttns;
import my.project.ttn_3.repository.CustomerRepository;
import my.project.ttn_3.repository.OrderRepository;
import my.project.ttn_3.service.CustomerService;
import my.project.ttn_3.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class TtnConverter {
    private final CustomerService customerService;
    private final OrderService orderService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final CustomerConverter customerConverter;
    private final OrderConverter orderConverter;

    public TtnResponse toResponse(Ttns ttns) {
        return new TtnResponse(ttns.getId(), ttns.getCustomers(), ttns.getOrders(),
                ttns.getAccessKey(), ttns.getCreateTtn());
    }

    public Ttns toTtn(TtnRequest ttnRequest) {
        CustomerRequest customerRequest = new CustomerRequest(ttnRequest.getName(), ttnRequest.getAddress(),
                ttnRequest.getRoute(),
                String.valueOf((ttnRequest.getName() + ttnRequest.getAddress() + ttnRequest.getRoute()).hashCode()));
        OrderRequest orderRequest = new OrderRequest(ttnRequest.getOrders());

        CustomerResponse customerResponse = customerService
                .findAndCreateCustomerByNameAndAddressAndRoute(customerRequest);

        OrderResponse orderResponse = orderService.createOrder(orderRequest);

        return new Ttns(0, customerResponseToCustomer(customerResponse),
                orderResponseToOrder(orderResponse),
                String.valueOf((ttnRequest.getName() + ttnRequest.getAddress() + ttnRequest.getRoute()).hashCode()),
                LocalDate.now());
    }

    public Customers customerResponseToCustomer(CustomerResponse response) {
        return new Customers(response.getId(), response.getName(), response.getAddress(), response.getRoute(),
                response.getAccessKey(), response.getDateVisit());
    }

    public Orders orderResponseToOrder(OrderResponse response) {
        return new Orders(response.getId(), response.getOrders(), response.getDateCreate());
    }

    public Ttns updateOrderByCustomer(Ttns ttn, TtnRequest request) {

        Customers customers1 = customerRepository.findCustomerByNameAndAddressAndRoute(
                request.getName(), request.getAddress(), request.getRoute());

        OrderRequest orderRequest = new OrderRequest(request.getOrders());
        Orders orders1 = orderRepository.save(orderConverter.toOrder(orderRequest));

        ttn.setCustomers(customers1);
        ttn.setOrders(orders1);
        ttn.setAccessKey(String.valueOf((request.getName() + request.getAddress() + request.getRoute() + LocalDateTime.now())
                .hashCode()));
        ttn.setCreateTtn(LocalDate.now());
        return ttn;
    }
}
