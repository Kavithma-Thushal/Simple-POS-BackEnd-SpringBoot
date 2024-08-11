package lk.ijse.gdse66.service;

import lk.ijse.gdse66.dto.OrdersDTO;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 9:44 PM - 8/10/2024
 **/
public interface PlaceOrderService {

    void placeOrder(OrdersDTO ordersDTO);

    List<OrdersDTO> loadAllOrders();
}