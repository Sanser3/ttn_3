package my.project.ttn_3.controller;

import jakarta.validation.Valid;
import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.converter.CustomerConverter;
import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        List<CustomerResponse> responses = customerService.findAllCustomer();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> addNewCustomer(@RequestBody CustomerRequest request){
        CustomerResponse response = customerService.createCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/route")
    public ResponseEntity<List<CustomerResponse>> getAllCustomerByRoute(@RequestBody CustomerRequest request){
        List<CustomerResponse> responses = customerService.findAllByRouteCustomer(request.getRoute());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<CustomerResponse> getCustomerAndCreateCustomerByNameAndAddressAndRoute(
            @RequestBody CustomerRequest request){
        CustomerResponse response = customerService.findAndCreateCustomerByNameAndAddressAndRoute(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomerResponse> updateCustomer(@RequestBody CustomerRequest request){
        CustomerResponse response = customerService.updateCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CustomerResponse> deleteCustomer(@RequestBody CustomerRequest request){
        CustomerResponse response = customerService.deleteCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
