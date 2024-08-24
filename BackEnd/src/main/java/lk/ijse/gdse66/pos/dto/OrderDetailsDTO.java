package lk.ijse.gdse66.pos.dto;

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
public class OrderDetailsDTO {
    private String orderId;
    private String itemCode;
    private Integer buyQty;
    private Double total;
}