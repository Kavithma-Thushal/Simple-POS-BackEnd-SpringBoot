package lk.ijse.gdse66.service.impl;

import lk.ijse.gdse66.dto.CustomerDTO;
import lk.ijse.gdse66.entity.CustomerEntity;
import lk.ijse.gdse66.repo.CustomerRepo;
import lk.ijse.gdse66.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-MySQL-CRUD
 * @since : 7:25 AM - 6/18/2024
 **/
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public ModelMapper modelMapper;

    @Autowired
    public CustomerRepo customerRepo;

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (!customerRepo.existsById(customerDTO.getId())) {
            customerRepo.save(modelMapper.map(customerDTO, CustomerEntity.class));
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        return customerRepo.findById(id).map(customerEntity -> modelMapper.map(customerEntity, CustomerDTO.class)).orElse(null);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerRepo.save(modelMapper.map(customerDTO, CustomerEntity.class));
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepo.deleteById(id);
    }

    @Override
    public List<CustomerEntity> loadAllCustomers() {
        return customerRepo.findAll();
    }
}
