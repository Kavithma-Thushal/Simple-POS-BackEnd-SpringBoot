package lk.ijse.gdse66.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 8:48 PM - 8/10/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersDTO {
    private String orderId;
    private String customerId;
    private List<OrderDetailsDTO> orderDetails;
}