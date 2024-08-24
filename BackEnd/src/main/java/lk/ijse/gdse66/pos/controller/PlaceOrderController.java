package lk.ijse.gdse66.pos.controller;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:42 PM - 8/10/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
public class PlaceOrderController {

    @Autowired
    private PlaceOrderService placeOrderService;

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderDTO orderDTO) {
        placeOrderService.placeOrder(orderDTO);
    }
}