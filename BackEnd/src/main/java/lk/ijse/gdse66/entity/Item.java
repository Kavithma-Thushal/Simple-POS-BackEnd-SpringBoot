package lk.ijse.gdse66.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 6:55 AM - 6/18/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item {

    @Id
    private String code;
    private String description;
    private double unitPrice;
    private int qtyOnHand;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetailsList;
}