package lk.ijse.gdse66.service.impl;

import lk.ijse.gdse66.dto.OrderDetailsDTO;
import lk.ijse.gdse66.dto.OrdersDTO;
import lk.ijse.gdse66.entity.Customer;
import lk.ijse.gdse66.entity.Orders;
import lk.ijse.gdse66.entity.OrderDetails;
import lk.ijse.gdse66.repo.CustomerRepo;
import lk.ijse.gdse66.repo.OrderRepo;
import lk.ijse.gdse66.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 9:44 PM - 8/10/2024
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void placeOrder(OrdersDTO ordersDTO) {

        // Check if the customer exists
        Optional<Customer> optionalCustomer = customerRepo.findById(ordersDTO.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            throw new RuntimeException("Customer Not Found...!");
        }

        // Map OrdersDTO to Orders entity
        Orders order = modelMapper.map(ordersDTO, Orders.class);
        order.setCustomer(optionalCustomer.get());

        // Initialize a list for OrderDetails entities
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        // Map each OrderDetailsDTO to OrderDetails entity and set the Orders reference
        for (OrderDetailsDTO detailsDTO : ordersDTO.getOrderDetailsList()) {
            OrderDetails orderDetails = modelMapper.map(detailsDTO, OrderDetails.class);
            orderDetails.setOrders(order); // Set the Orders reference
            orderDetailsList.add(orderDetails);
        }

        // Set the OrderDetails list to the Orders entity
        order.setOrderDetailsList(orderDetailsList);

        // Save the Orders entity which will also save the OrderDetails due to CascadeType.ALL
        orderRepo.save(order);
    }
}