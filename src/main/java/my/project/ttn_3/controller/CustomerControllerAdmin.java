package my.project.ttn_3.controller;

import lombok.AllArgsConstructor;
import my.project.ttn_3.DTO.customers.CustomerRequest;
import my.project.ttn_3.DTO.customers.CustomerResponse;
import my.project.ttn_3.DTO.ttns.TtnRequest;
import my.project.ttn_3.DTO.ttns.TtnResponse;
import my.project.ttn_3.converter.CustomerConverter;
import my.project.ttn_3.entity.Customers;
import my.project.ttn_3.entity.OrderData;
import my.project.ttn_3.repository.CustomerRepository;
import my.project.ttn_3.service.CustomerService;
import my.project.ttn_3.service.TtnService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerControllerAdmin {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;
    private final CustomerRepository customerRepository;
    private final TtnService ttnService;
    private final CustomerController customerController;
    private final TtnController ttnController;

    @GetMapping("/addcustomer")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customers", new Customers());
        return "addcustomer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(@ModelAttribute("customers") Customers customer, Model model) {
        try {
            model.addAttribute("customers", customer);
            CustomerRequest request = customerConverter.toRequest(customer);
            CustomerResponse response = customerService.createCustomer(request);
            model.addAttribute("response", response);
            if (response.getId() == 0) {
                return "indexLog";
            }
        } catch (Exception e) {
            return "indexLog";
        }
        return "index";
    }

    @GetMapping("/updatecustomer")
    public String showUpdateCustomerForm(Model model) {
        model.addAttribute("customers", new Customers());
        model.addAttribute("newCustomers", new Customers());
        return "updatecustomer";
    }

    @PutMapping("/updatecustomer")
    public String updateCustomer(@ModelAttribute("customers") Customers customers, Customers newCustomers, Model model) {
        try {
            model.addAttribute("customers", customers);
            model.addAttribute("newCustomers", newCustomers);
            Customers customer = customerRepository.findCustomerByNameAndAddressAndRoute(
                    customers.getName(), customers.getAddress(), customers.getRoute());
            CustomerRequest request = new CustomerRequest(
                    newCustomers.getName(), newCustomers.getAddress(), newCustomers.getRoute(), customer.getAccessKey());
            CustomerResponse response = customerService.updateCustomer(request);
            model.addAttribute("response", response);
        } catch (Exception e) {
            return "indexLogUp";
        }
        return "indexUp";
    }

    @GetMapping("/addttn")
    public String showAddTtnForm(Model model) {
        model.addAttribute("ttns", new TtnRequest());
        return "addttn";
    }

    @PostMapping("/addttn")
    public String addTtn(@ModelAttribute("ttns") TtnRequest ttns, Model model) {
        try {
            model.addAttribute("ttns", ttns);

            List<Integer> ttnsOrder = new ArrayList<>();
            ttnsOrder.add(ttns.getOrders().get(0));
            ttnsOrder.add(ttns.getOrders().get(1));
            ttnsOrder.add(ttns.getOrders().get(2));

            TtnRequest ttnRequest = new TtnRequest(ttns.getName(), ttns.getAddress(), ttns.getRoute(),
                    "0", LocalDate.now(), ttnsOrder);
            TtnResponse response = ttnService.createTtn(ttnRequest);
            model.addAttribute("response", response);
            if (response.getId() == 0) {
                return "indexLogTtn";
            }
        } catch (Exception e) {
            return "indexLogTtn";
        }
        return "indexTtn";
    }

    @GetMapping("/submitOrder")
    public String showOrderForm(Model model) {
        model.addAttribute("orderForm", new OrderData());
        return "submitOrder";
    }

    @PostMapping("/submitOrder")
    public ResponseEntity<TtnResponse> submitOrder(@RequestBody OrderData orderData, Model model) {
        model.addAttribute("orderForm", orderData);
        TtnRequest ttnRequest = new TtnRequest(orderData.getName(), orderData.getAddress(), orderData.getRoute(),
                "0", LocalDate.now(), orderData.getOrders());
        TtnResponse response = ttnService.createTtn(ttnRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
//        model.addAttribute("response", response);
//        return "indexOrder";
    }

    @GetMapping("/getroutecustom")
    public String showFindRouteFormCustom(Model model) {
        model.addAttribute("route", new CustomerRequest());
        return "getroutecustom";
    }

    @PostMapping("/getroutecustom")
    public ResponseEntity<List<CustomerResponse>> getAllByRouteCustom(@ModelAttribute("route") CustomerRequest request, Model model) {
        model.addAttribute("route", request);
        return customerController.getAllCustomerByRoute(request);
    }

    @GetMapping("/getroutettn")
    public String showFindRouteFormTtn(Model model) {
        model.addAttribute("route", new TtnRequest());
        return "getroutettn";
    }

    @PostMapping("/getroutettn")
    public ResponseEntity<List<TtnResponse>> getAllByRouteTtn(@ModelAttribute("route") TtnRequest request, Model model) {
        model.addAttribute("route", request);
        List<TtnResponse> responses = ttnController.getAllTtnByRoute(request);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/getdatettn")
    public String showFindDateFormTtn(Model model) {
        model.addAttribute("date", new TtnRequest());
        return "getdatettn";
    }

    @PostMapping("/getdatettn")
    public ResponseEntity<List<TtnResponse>> getAllByDateTtn(@ModelAttribute("date") TtnRequest request, Model model) {
        model.addAttribute("date", request);
        List<TtnResponse> responses = ttnController.getAllTtnByDate(request);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}


