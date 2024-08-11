package lk.ijse.gdse66.repo;

import lk.ijse.gdse66.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:35 AM - 6/18/2024
 **/
public interface ItemRepo extends JpaRepository<Item, String> {
}
