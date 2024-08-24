package lk.ijse.gdse66.pos.repo;

import lk.ijse.gdse66.pos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:35 AM - 6/18/2024
 **/
public interface CustomerRepo extends JpaRepository<Customer, String> {
    Optional<Customer> findTopByOrderByIdDesc();
}