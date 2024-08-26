package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.util.ResponseUtil;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:44 PM - 8/10/2024
 **/
public interface PlaceOrderService {

    ResponseUtil<String> placeOrder(OrderDTO orderDTO);

    ResponseUtil<String> generateOrderId();

    ResponseUtil<Integer> getOrderCount();
}