package lk.ijse.gdse66.controller;

import lk.ijse.gdse66.dto.OrdersDTO;
import lk.ijse.gdse66.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 9:42 PM - 8/10/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrdersDTO ordersDTO) {
        orderService.placeOrder(ordersDTO);
    }
}