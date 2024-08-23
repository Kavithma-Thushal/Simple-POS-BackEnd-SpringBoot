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
    public ResponseEntity<?> searchCustomer(@PathVariable String id) {
        try {
            CustomerDTO customerDTO = customerService.searchCustomer(id);
            return new ResponseEntity<>(customerDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        String response = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String id) {
        String response = customerService.deleteCustomer(id);
        if (response.equals("Customer Deleted Successfully...!")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/loadAllCustomers")
    public ResponseEntity<List<CustomerDTO>> loadAllCustomers() {
        List<CustomerDTO> customerDTOList = customerService.loadAllCustomers();
        if (customerDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerDTOList, HttpStatus.OK);
    }
}
