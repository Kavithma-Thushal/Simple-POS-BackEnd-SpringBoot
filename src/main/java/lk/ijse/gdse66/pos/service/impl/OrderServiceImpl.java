package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.dto.OrderDetailsDTO;
import lk.ijse.gdse66.pos.entity.Item;
import lk.ijse.gdse66.pos.entity.OrderDetails;
import lk.ijse.gdse66.pos.entity.Orders;
import lk.ijse.gdse66.pos.repo.CustomerRepo;
import lk.ijse.gdse66.pos.repo.ItemRepo;
import lk.ijse.gdse66.pos.repo.OrderRepo;
import lk.ijse.gdse66.pos.service.OrderService;
import lk.ijse.gdse66.pos.util.EmailSender;
import lk.ijse.gdse66.pos.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 9:44 PM - 8/10/2024
 **/
@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailSender emailSender;

    @Override
    public ResponseUtil<String> placeOrder(OrderDTO orderDTO) {

        // Check if the OrderId already exists
        if (orderRepo.existsById(orderDTO.getOrderId())) {
            String errorResponse = "Duplicate Order Id: " + orderDTO.getOrderId();
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new ResponseUtil<>(errorResponse, HttpStatus.CONFLICT, null);
        }

        // Set Customer
        Orders order = modelMapper.map(orderDTO, Orders.class);
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

            OrderDetails orderDetails = modelMapper.map(orderDetailsDTO, OrderDetails.class);
            orderDetails.setOrders(order);
            orderDetails.setItem(item);
//            orderDetails.setTotal(item.getUnitPrice() * orderDetailsDTO.getBuyQty());
            orderDetailsList.add(orderDetails);

            // Update Item Quantity
            item.setQtyOnHand(item.getQtyOnHand() - orderDetailsDTO.getBuyQty());
            itemRepo.save(item);
        }

        // Place Order
        order.setOrderDetailsList(orderDetailsList);
        orderRepo.save(order);

        String successResponse = "Order Placed Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
//        try {
//            emailSender.sendEmail("kavithmathushal451@gmail.com", "Order Management", successResponse);
//        } catch (Exception e) {
//            log.error("\u001B[31m{}\u001B[0m", "Failed to Send Email...!");
//        }
        return new ResponseUtil<>(successResponse, HttpStatus.OK, null);
    }

    @Override
    public ResponseUtil<String> generateOrderId() {
        String lastOrderId = orderRepo.findTopByOrderByOrderIdDesc().map(Orders::getOrderId).orElse("ORD-000");

        String successResponse = "Last Order ID Retrieved Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        return new ResponseUtil<>(successResponse, HttpStatus.OK, lastOrderId);
    }

    @Override
    public ResponseUtil<Integer> getOrderCount() {
        Integer orderCount = orderRepo.countBy();

        String successResponse = "Order Count Retrieved Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        return new ResponseUtil<>(successResponse, HttpStatus.OK, orderCount);
    }

    @Override
    public ResponseUtil<List<OrderDTO>> loadOrderDetails() {
        List<Orders> ordersList = orderRepo.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (Orders order : ordersList) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderId(order.getOrderId());
            orderDTO.setCustomerId(order.getCustomer().getId());
            List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();

            for (OrderDetails orderDetails : order.getOrderDetailsList()) {
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                orderDetailsDTO.setItemCode(orderDetails.getItem().getCode());
                orderDetailsDTO.setBuyQty(orderDetails.getBuyQty());
                orderDetailsDTO.setTotal(orderDetails.getTotal());
                orderDetailsDTOList.add(orderDetailsDTO);
            }

            orderDTO.setOrderDetailsList(orderDetailsDTOList);
            orderDTOList.add(orderDTO);
        }

        String successResponse = "All Order Details Retrieved Successfully...!";
        log.info("\u001B[34m{}\u001B[0m", successResponse);
        return new ResponseUtil<>(successResponse, HttpStatus.OK, orderDTOList);
    }
}