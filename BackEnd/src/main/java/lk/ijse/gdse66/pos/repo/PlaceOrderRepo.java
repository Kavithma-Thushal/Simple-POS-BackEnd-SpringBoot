package lk.ijse.gdse66.pos.repo;

import lk.ijse.gdse66.pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:46 PM - 8/10/2024
 **/
public interface PlaceOrderRepo extends JpaRepository<Orders, String> {
}