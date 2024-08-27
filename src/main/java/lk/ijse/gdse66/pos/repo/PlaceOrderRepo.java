package lk.ijse.gdse66.pos.repo;

import lk.ijse.gdse66.pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 9:46 PM - 8/10/2024
 **/
public interface PlaceOrderRepo extends JpaRepository<Orders, String> {

    // Generate Order ID
    Optional<Orders> findTopByOrderByOrderIdDesc();

    // Get Order Count
    Integer countBy();
}