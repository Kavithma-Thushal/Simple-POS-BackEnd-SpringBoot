package lk.ijse.gdse66.api;

import lk.ijse.gdse66.dto.CustomerDTO;
import lk.ijse.gdse66.entity.CustomerEntity;
import lk.ijse.gdse66.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-MySQL-CRUD
 * @since : 7:05 AM - 6/18/2024
 **/
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerAPI {

    @Autowired
    public CustomerService customerService;

    @PostMapping("/saveCustomer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
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
    public List<CustomerEntity> loadAllCustomers() {
        return customerService.loadAllCustomers();
    }
}
