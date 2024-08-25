package lk.ijse.gdse66.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 8:48 PM - 8/10/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(OrderDetailsId.class)
public class OrderDetails {

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "item_code", nullable = false)
    private Item item;

    private Integer buyQty;
    private Double total;
}