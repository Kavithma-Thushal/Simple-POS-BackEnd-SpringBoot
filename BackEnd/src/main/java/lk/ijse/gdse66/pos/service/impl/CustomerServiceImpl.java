package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.entity.Customer;
import lk.ijse.gdse66.pos.repo.CustomerRepo;
import lk.ijse.gdse66.pos.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveCustomer(CustomerDTO customerDTO) {
        if (!customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        Optional<Customer> deleteId = customerRepo.findById(id);
        return deleteId.map(customer -> modelMapper.map(customer, CustomerDTO.class)).orElse(null);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, Customer.class));
        }
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerDTO> loadAllCustomers() {
        List<Customer> customerList = customerRepo.findAll();
        return customerList.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
}
