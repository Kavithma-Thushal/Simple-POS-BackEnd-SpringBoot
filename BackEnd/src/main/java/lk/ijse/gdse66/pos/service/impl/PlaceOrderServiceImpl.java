package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.dto.OrderDetailsDTO;
import lk.ijse.gdse66.pos.entity.Item;
import lk.ijse.gdse66.pos.entity.OrderDetails;
import lk.ijse.gdse66.pos.entity.Orders;
import lk.ijse.gdse66.pos.repo.CustomerRepo;
import lk.ijse.gdse66.pos.repo.ItemRepo;
import lk.ijse.gdse66.pos.repo.PlaceOrderRepo;
import lk.ijse.gdse66.pos.service.PlaceOrderService;
import lk.ijse.gdse66.pos.util.EmailSender;
import lk.ijse.gdse66.pos.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:44 PM - 8/10/2024
 **/
@Slf4j
@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private PlaceOrderRepo placeOrderRepo;

    @Autowired
    private EmailSender emailSender;

    @Override
    public ResponseUtil<String> placeOrder(OrderDTO orderDTO) {

        // Check if the OrderId already exists
        if (placeOrderRepo.existsById(orderDTO.getOrderId())) {
            String errorResponse = "Duplicate Order Id: " + orderDTO.getOrderId();
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.CONFLICT, null);
        }

        // Set OrderId & Customer
        Orders order = new Orders();
        order.setOrderId(orderDTO.getOrderId());
        order.setCustomer(customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer Not Found: " + orderDTO.getCustomerId())));

        // Set Item
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : orderDTO.getOrderDetailsList()) {
            Item item = itemRepo.findById(orderDetailsDTO.getItemCode())
                    .orElseThrow(() -> new RuntimeException("Item Not Found: " + orderDetailsDTO.getItemCode()));

            if (item.getQtyOnHand() < orderDetailsDTO.getBuyQty()) {
                throw new RuntimeException("Not Enough Stock For Item: " + item.getDescription());
            }

            double total = item.getUnitPrice() * orderDetailsDTO.getBuyQty();

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrders(order);
            orderDetails.setItem(item);
            orderDetails.setBuyQty(orderDetailsDTO.getBuyQty());
            orderDetails.setTotal(total);
            orderDetailsList.add(orderDetails);

            item.setQtyOnHand(item.getQtyOnHand() - orderDetailsDTO.getBuyQty());
            itemRepo.save(item);
        }

        order.setOrderDetailsList(orderDetailsList);
        placeOrderRepo.save(order);

        String successResponse = "Order Placed Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        try {
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Order Management", successResponse);
        } catch (Exception e) {
            log.error("\u001B[31m{}\u001B[0m", "Failed to Send Email...!");
        }
        return new ResponseUtil<>(successResponse, HttpStatus.OK, null);
    }
}