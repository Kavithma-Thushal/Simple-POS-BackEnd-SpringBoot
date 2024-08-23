package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.entity.Customer;
import lk.ijse.gdse66.pos.repo.CustomerRepo;
import lk.ijse.gdse66.pos.service.CustomerService;
import lk.ijse.gdse66.pos.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:25 AM - 6/18/2024
 **/
@Slf4j
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Response<String> saveCustomer(CustomerDTO customerDTO) {

        if (!customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));

            String successResponse = "Customer Saved Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Customer Already Exists...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public Response<CustomerDTO> searchCustomer(String id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);

            String successResponse = "Customer Searched Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, customerDTO);

        } else {
            String errorResponse = "Customer Not Found...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public Response<String> updateCustomer(CustomerDTO customerDTO) {

        if (customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));

            String successResponse = "Customer Updated Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Customer Not Found...!";
            log.info("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public Response<String> deleteCustomer(String id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {

            customerRepo.deleteById(id);
            String successResponse = "Customer Deleted Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Customer Not Found...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public Response<List<CustomerDTO>> loadAllCustomers() {

        List<Customer> customerList = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());

        if (!customerDTOList.isEmpty()) {
            String successResponse = "Customers Loaded Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, customerDTOList);
        } else {
            String errorResponse = "Customers Not Found...!";
            log.info("\u001B[33m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NO_CONTENT, null);
        }
    }
}
