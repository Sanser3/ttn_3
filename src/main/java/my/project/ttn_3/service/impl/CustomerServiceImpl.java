package my.project.ttn_3.service.impl;

import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.converter.CustomerConverter;
import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.repository.CustomerRepository;
import my.project.ttn_3.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    public CustomerResponse createCustomer(CustomerRequest customerRequest){
        Customers newCustomer = customerConverter.toCustomer(customerRequest);
        if (checkNewCustomer(newCustomer)){
            newCustomer = customerRepository.save(newCustomer);
            return new CustomerResponse(newCustomer.getId(), newCustomer.getName(), newCustomer.getAddress(),
                    newCustomer.getRoute(), newCustomer.getAccessKey(), LocalDate.now());
        }
        return new CustomerResponse(0, newCustomer.getName(), newCustomer.getAddress(),newCustomer.getRoute(),
                "Customer already exist", LocalDate.now());
    }

    public boolean checkNewCustomer(Customers customer){
        return customerRepository.findByNameAndAddressAndRoute(customer.getName(), customer.getAddress(),
                customer.getRoute()).isEmpty();
    }

    public List<CustomerResponse> findAllCustomer(){
        List<Customers> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerConverter::toResponse)
                .collect(Collectors.toList());
    }
    public List<CustomerResponse> findAllByRouteCustomer(String route){
        List<Customers> customers = customerRepository.findAllByRoute(route)
                .orElseThrow(() -> new IllegalAccessError("Customer not found"));
        return customers.stream()
                .map(customerConverter::toResponse)
                .collect(Collectors.toList());
    }

    public CustomerResponse findAndCreateCustomerByNameAndAddressAndRoute(CustomerRequest request){
        Customers customer = customerRepository.findCustomerByNameAndAddressAndRoute(request.getName(),
                request.getAddress(), request.getRoute());
        if(customer == null){
            customer = customerRepository.save(customerConverter.toCustomer(request));
        }
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getAddress(), customer.getRoute(),
                customer.getAccessKey(), customer.getDateVisit());
    }

    public CustomerResponse updateCustomer(CustomerRequest request){
        Customers customerFourUpdate = customerRepository.findByAccessKey(request.getAccessKey())
                .orElseThrow(() -> new IllegalAccessError("Customer not found"));

        customerFourUpdate = customerConverter.updateCustomer(request, customerFourUpdate);
        customerRepository.save(customerFourUpdate);
        return new CustomerResponse(customerFourUpdate.getId(), customerFourUpdate.getName(),
                customerFourUpdate.getAddress(), customerFourUpdate.getRoute(),
                customerFourUpdate.getAccessKey(), LocalDate.now());
    }

    public CustomerResponse deleteCustomer(CustomerRequest request){
        Customers customer = customerRepository.findByAccessKey(request.getAccessKey())
                .orElseThrow(() -> new IllegalAccessError("Customer not found"));
        customerRepository.delete(customer);
        return new CustomerResponse(customer.getId(), customer.getName(),
                customer.getAddress(), customer.getRoute(),
                customer.getAccessKey(), LocalDate.now());
    }

}
