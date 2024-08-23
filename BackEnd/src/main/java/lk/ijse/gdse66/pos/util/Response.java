package lk.ijse.gdse66.pos.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 9:19 PM - 8/23/2024
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {
    private String message;
    private HttpStatus status;
    private T data;
}