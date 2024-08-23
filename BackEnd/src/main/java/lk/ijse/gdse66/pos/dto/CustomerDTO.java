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
public class CustomerDTO {

    @Pattern(regexp = "C\\d{2}-\\d{3}", message = "Customer ID format must be 'C00-001', 'C12-345'")
    private String id;

    @Pattern(regexp = "^[A-Za-z\\s'-]{4,}$", message = "Name must contain at least 4 letters")
    private String name;

    @Pattern(regexp = "^[A-Za-z\\s'-]{4,}$", message = "Address must contain at least 4 letters")
    private String address;

    @NotNull(message = "Salary can't be null")
    @Min(value = 0, message = "Salary must be a positive value or zero")
    private Double salary;
}
