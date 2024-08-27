package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.entity.Customer;
import lk.ijse.gdse66.pos.repo.CustomerRepo;
import lk.ijse.gdse66.pos.service.CustomerService;
import lk.ijse.gdse66.pos.util.EmailSender;
import lk.ijse.gdse66.pos.util.ResponseUtil;
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
 * @project : Simple-POS-BackEnd-SpringBoot
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

    @Autowired
    private EmailSender emailSender;

    @Override
    public ResponseUtil<String> saveCustomer(CustomerDTO customerDTO) {

        if (!customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));

            String successResponse = "Customer Saved Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
//            try {
//                emailSender.sendEmail("kavithmathushal451@gmail.com", "Customer Management", successResponse);
//            } catch (Exception e) {
//                log.error("\u001B[31m{}\u001B[0m", "Failed to Send Email...!");
//            }
            return new ResponseUtil<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Duplicate Customer Id: " + customerDTO.getId();
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public ResponseUtil<CustomerDTO> searchCustomer(String id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);

            String successResponse = "Customer Searched Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new ResponseUtil<>(successResponse, HttpStatus.OK, customerDTO);

        } else {
            String errorResponse = "Customer Not Found: "+id;
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseUtil<String> updateCustomer(CustomerDTO customerDTO) {

        if (customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));

            String successResponse = "Customer Updated Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new ResponseUtil<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Customer Not Found: "+customerDTO.getId();
            log.info("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public ResponseUtil<String> deleteCustomer(String id) {

        Optional<Customer> customer = customerRepo.findById(id);
        if (customer.isPresent()) {

            customerRepo.deleteById(id);
            String successResponse = "Customer Deleted Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new ResponseUtil<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Customer Not Found: "+id;
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public ResponseUtil<List<CustomerDTO>> loadAllCustomers() {

        List<Customer> customerList = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());

        if (!customerDTOList.isEmpty()) {
            String successResponse = "Customers Loaded Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            return new ResponseUtil<>(successResponse, HttpStatus.OK, customerDTOList);

        } else {
            String errorResponse = "No Customers Found in DB";
            log.info("\u001B[33m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.NO_CONTENT, null);
        }
    }

    @Override
    public ResponseUtil<String> generateCustomerId() {
        String lastCustomerId = customerRepo.findTopByOrderByIdDesc().map(Customer::getId).orElse("C00-000");

        String successResponse = "Last Customer ID Retrieved Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        return new ResponseUtil<>(successResponse, HttpStatus.OK, lastCustomerId);
    }

    @Override
    public ResponseUtil<Integer> getCustomerCount() {
        Integer customerCount = customerRepo.countBy();

        String successResponse = "Customer Count Retrieved Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        return new ResponseUtil<>(successResponse, HttpStatus.OK, customerCount);
    }
}