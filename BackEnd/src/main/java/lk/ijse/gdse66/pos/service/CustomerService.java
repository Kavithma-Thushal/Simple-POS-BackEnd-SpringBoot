package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.util.Response;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:24 AM - 6/18/2024
 **/
public interface CustomerService {

    Response<String> saveCustomer(CustomerDTO customerDTO);

    Response<CustomerDTO> searchCustomer(String id);

    Response<String> updateCustomer(CustomerDTO customerDTO);

    Response<String> deleteCustomer(String id);

    Response<List<CustomerDTO>> loadAllCustomers();
}
