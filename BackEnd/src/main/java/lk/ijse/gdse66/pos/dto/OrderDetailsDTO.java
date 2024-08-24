package lk.ijse.gdse66.pos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Buy Qty can't be null")
    @Min(value = 0, message = "Buy Qty must be a positive value or zero")
    private Integer buyQty;

    @NotNull(message = "Total can't be null")
    @Min(value = 0, message = "Total must be a positive value or zero")
    private Double total;
}