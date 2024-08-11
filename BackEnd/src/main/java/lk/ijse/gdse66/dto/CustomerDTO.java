package lk.ijse.gdse66.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Kavithma Thushal
 * @project : SpringBoot-CRUD
 * @since : 6:55 AM - 6/18/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private double salary;
}
