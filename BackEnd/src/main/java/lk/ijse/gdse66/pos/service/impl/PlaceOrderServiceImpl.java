package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.OrderDTO;
import lk.ijse.gdse66.pos.entity.Item;
import lk.ijse.gdse66.pos.entity.OrderDetails;
import lk.ijse.gdse66.pos.entity.Orders;
import lk.ijse.gdse66.pos.repo.ItemRepo;
import lk.ijse.gdse66.pos.repo.PlaceOrderRepo;
import lk.ijse.gdse66.pos.service.PlaceOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:44 PM - 8/10/2024
 **/
@Service
@Transactional
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Autowired
    private PlaceOrderRepo placeOrderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void placeOrder(OrderDTO orderDTO) {
        Orders order = modelMapper.map(orderDTO, Orders.class);

        for (OrderDetails orderDetail : order.getOrderDetailsList()) {
            orderDetail.setOrders(order);

            // Update Item Qty
            Item item = itemRepo.findById(orderDetail.getItem().getCode()).orElse(null);
            item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getBuyQty());
            itemRepo.save(item);
        }
        placeOrderRepo.save(order);
    }

    @Override
    public List<OrderDTO> loadAllOrders() {
        List<Orders> orderList = placeOrderRepo.findAll();
        return orderList.stream()
                .map(orders -> modelMapper.map(orders, OrderDTO.class))
                .collect(Collectors.toList());
    }
}