package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.OrderDTO;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:44 PM - 8/10/2024
 **/
public interface PlaceOrderService {

    void placeOrder(OrderDTO orderDTO);
}