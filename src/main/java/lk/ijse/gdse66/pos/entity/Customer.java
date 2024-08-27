package lk.ijse.gdse66.pos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 6:55 AM - 6/18/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    private String id;
    private String name;
    private String address;
    private Double salary;
}