package lk.ijse.gdse66.api;

import lk.ijse.gdse66.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-MySQL-CRUD
 * @since : 7:05 AM - 6/18/2024
 **/
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerAPI {

    @GetMapping("/getCustomer")
    public String getCustomer() {
        return "Kavithma Thushal";
    }

    @PostMapping("/saveCustomer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        System.out.println(customerDTO.getId());
        System.out.println(customerDTO.getName());
        System.out.println(customerDTO.getAddress());
        System.out.println(customerDTO.getSalary());
    }
}
