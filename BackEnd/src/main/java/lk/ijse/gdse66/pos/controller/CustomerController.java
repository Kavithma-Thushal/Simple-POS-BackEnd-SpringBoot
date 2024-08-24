package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.service.CustomerService;
import lk.ijse.gdse66.pos.util.ResponseUtil;
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
    public ResponseEntity<ResponseUtil<String>> saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        ResponseUtil<String> responseUtil = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/searchCustomer/{id}")
    public ResponseEntity<ResponseUtil<CustomerDTO>> searchCustomer(@PathVariable String id) {
        ResponseUtil<CustomerDTO> responseUtil = customerService.searchCustomer(id);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<ResponseUtil<String>> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        ResponseUtil<String> responseUtil = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public ResponseEntity<ResponseUtil<String>> deleteCustomer(@PathVariable String id) {
        ResponseUtil<String> responseUtil = customerService.deleteCustomer(id);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/loadAllCustomers")
    public ResponseEntity<ResponseUtil<List<CustomerDTO>>> loadAllCustomers() {
        ResponseUtil<List<CustomerDTO>> responseUtil = customerService.loadAllCustomers();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/generateCustomerId")
    public ResponseEntity<ResponseUtil<String>> generateCustomerId() {
        ResponseUtil<String> responseUtil = customerService.generateCustomerId();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }
}