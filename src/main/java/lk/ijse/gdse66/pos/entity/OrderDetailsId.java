package lk.ijse.gdse66.pos.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 1:23 PM - 8/25/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailsId implements Serializable {
    private String orders;
    private String item;
}