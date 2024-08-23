package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        String response = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchCustomer/{id}")
    public CustomerDTO searchCustomer(@PathVariable String id) {
        return customerService.searchCustomer(id);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/loadAllCustomers")
    public List<CustomerDTO> loadAllCustomers() {
        return customerService.loadAllCustomers();
    }
}
