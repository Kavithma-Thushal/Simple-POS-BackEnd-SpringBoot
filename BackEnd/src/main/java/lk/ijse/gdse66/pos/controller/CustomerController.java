package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.service.CustomerService;
import lk.ijse.gdse66.pos.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Response<String>> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Response<String> response = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/searchCustomer/{id}")
    public ResponseEntity<Response<CustomerDTO>> searchCustomer(@PathVariable String id) {
            Response<CustomerDTO> response = customerService.searchCustomer(id);
            return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Response<String>> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Response<String> response = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<Response<String>> deleteCustomer(@PathVariable String id) {
        Response<String> response = customerService.deleteCustomer(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/loadAllCustomers")
    public ResponseEntity<Response<List<CustomerDTO>>> loadAllCustomers() {
        Response<List<CustomerDTO>> response = customerService.loadAllCustomers();
        return new ResponseEntity<>(response, response.getStatus());
    }
}
