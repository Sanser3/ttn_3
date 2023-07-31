package my.project.ttn_3.service;

import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.entity.Customers;

import java.util.List;

public interface CustomerService {

    CustomerResponse createCustomer(CustomerRequest customerRequest);

    boolean checkNewCustomer(Customers customer);

    List<CustomerResponse> findAllCustomer();

    List<CustomerResponse> findAllByRouteCustomer(String route);

    CustomerResponse findAndCreateCustomerByNameAndAddressAndRoute(CustomerRequest request);

    CustomerResponse updateCustomer(CustomerRequest request);

    CustomerResponse deleteCustomer(CustomerRequest request);

}
