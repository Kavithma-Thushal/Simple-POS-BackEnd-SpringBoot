package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.util.ResponseUtil;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 7:24 AM - 6/18/2024
 **/
public interface CustomerService {

    ResponseUtil<String> saveCustomer(CustomerDTO customerDTO);

    ResponseUtil<CustomerDTO> searchCustomer(String id);

    ResponseUtil<String> updateCustomer(CustomerDTO customerDTO);

    ResponseUtil<String> deleteCustomer(String id);

    ResponseUtil<List<CustomerDTO>> loadAllCustomers();

    ResponseUtil<String> generateCustomerId();

    ResponseUtil<Integer> getCustomerCount();
}