package lk.ijse.gdse66.controller;

import lk.ijse.gdse66.dto.OrderDTO;
import lk.ijse.gdse66.service.PlaceOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 9:42 PM - 8/10/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
public class PlaceOrderController {

    @Autowired
    public PlaceOrderService placeOrderService;

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderDTO orderDTO) {
        placeOrderService.placeOrder(orderDTO);
    }

    @GetMapping("/loadAllOrders")
    public List<OrderDTO> loadAllOrders() {
        return placeOrderService.loadAllOrders();
    }
}