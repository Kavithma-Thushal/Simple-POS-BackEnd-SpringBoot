package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.service.PlaceOrderService;
import lk.ijse.gdse66.pos.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 9:42 PM - 8/10/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/orders")
public class PlaceOrderController {

    @Autowired
    private PlaceOrderService placeOrderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseUtil<String>> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
        ResponseUtil<String> responseUtil = placeOrderService.placeOrder(orderDTO);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/generateOrderId")
    public ResponseEntity<ResponseUtil<String>> generateOrderId() {
        ResponseUtil<String> responseUtil = placeOrderService.generateOrderId();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/getOrderCount")
    public ResponseEntity<ResponseUtil<Integer>> getOrderCount() {
        ResponseUtil<Integer> responseUtil = placeOrderService.getOrderCount();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/loadOrderDetails")
    public ResponseEntity<ResponseUtil<List<OrderDTO>>> loadOrderDetails() {
        ResponseUtil<List<OrderDTO>> responseUtil = placeOrderService.loadOrderDetails();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }
}