package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.util.ResponseUtil;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 9:44 PM - 8/10/2024
 **/
public interface OrderService {

    ResponseUtil<String> placeOrder(OrderDTO orderDTO);

    ResponseUtil<String> generateOrderId();

    ResponseUtil<Integer> getOrderCount();

    ResponseUtil<List<OrderDTO>> loadOrderDetails();
}