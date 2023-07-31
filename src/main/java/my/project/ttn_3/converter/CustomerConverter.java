package my.project.ttn_3.converter;

import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.entity.Customers;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Data
@AllArgsConstructor
public class CustomerConverter {
    public Customers toCustomer(CustomerRequest customerRequest){
        return new Customers(0,customerRequest.getName(), customerRequest.getAddress(), customerRequest.getRoute(),
                String.valueOf((customerRequest.getName()+customerRequest.getAddress()+customerRequest.getRoute()).hashCode()),
                LocalDate.now());
    }

    public CustomerResponse toResponse(Customers customers){
        return new CustomerResponse(customers.getId(), customers.getName(),
                customers.getAddress(), customers.getRoute(), customers.getAccessKey(), customers.getDateVisit());
    }

    public Customers updateCustomer(CustomerRequest customerRequest, Customers existCustomer){
        existCustomer.setName(customerRequest.getName());
        existCustomer.setAddress(customerRequest.getAddress());
        existCustomer.setRoute(customerRequest.getRoute());
        existCustomer.setAccessKey(String.valueOf((customerRequest.getName()+customerRequest.getAddress()).hashCode()));
        existCustomer.setDateVisit(LocalDate.now());
        return existCustomer;
    }

    public CustomerRequest toRequest(Customers customers){
        return new CustomerRequest(customers.getName(), customers.getAddress(), customers.getRoute(), "0");
    }

}
