package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.CustomerDTO;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:24 AM - 6/18/2024
 **/
public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO);

    CustomerDTO searchCustomer(String id);

    void updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(String id);

    List<CustomerDTO> loadAllCustomers();
}
