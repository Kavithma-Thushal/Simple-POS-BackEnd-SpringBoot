package lk.ijse.gdse66.service;

import lk.ijse.gdse66.dto.OrderDTO;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:44 PM - 8/10/2024
 **/
public interface PlaceOrderService {

    void placeOrder(OrderDTO orderDTO);

    List<OrderDTO> loadAllOrders();
}