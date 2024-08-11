package lk.ijse.gdse66.service.impl;

import lk.ijse.gdse66.dto.OrdersDTO;
import lk.ijse.gdse66.entity.Item;
import lk.ijse.gdse66.entity.OrderDetails;
import lk.ijse.gdse66.entity.Orders;
import lk.ijse.gdse66.repo.ItemRepo;
import lk.ijse.gdse66.repo.OrderRepo;
import lk.ijse.gdse66.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 9:44 PM - 8/10/2024
 **/
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void placeOrder(OrdersDTO ordersDTO) {
        Orders order = modelMapper.map(ordersDTO, Orders.class);

        for (OrderDetails orderDetail : order.getOrderDetailsList()) {
            orderDetail.setOrders(order);

            // Update Item Qty
            Item item = itemRepo.findById(orderDetail.getItem().getCode()).orElse(null);
            item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getBuyQty());
            itemRepo.save(item);
        }
        orderRepo.save(order);
    }
}