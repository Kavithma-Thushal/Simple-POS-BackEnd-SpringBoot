package lk.ijse.gdse66.pos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 6:55 AM - 6/18/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {

    @Pattern(regexp = "I\\d{2}-\\d{3}", message = "Item Code format must be 'I00-001', 'I12-345'")
    private String code;

    @Pattern(regexp = "^[A-Za-z\\s'-]{4,}$", message = "Description must contain at least 4 letters")
    private String description;

    @NotNull(message = "Unit Price can't be null")
    @Min(value = 0, message = "Unit Price must be a positive value or zero")
    private Double unitPrice;

    @NotNull(message = "Quantity On Hand can't be null")
    @Min(value = 0, message = "Quantity On Hand must be a positive value or zero")
    private Integer qtyOnHand;
}
