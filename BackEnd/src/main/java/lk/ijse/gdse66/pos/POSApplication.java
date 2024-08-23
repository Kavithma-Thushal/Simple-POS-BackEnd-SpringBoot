package lk.ijse.gdse66.pos;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:24 AM - 6/18/2024
 **/
@Slf4j
@SpringBootApplication
public class POSApplication {

    public static void main(String[] args) {
        SpringApplication.run(POSApplication.class, args);
        log.error("\u001B[34m{}\u001B[0m", "POS Application Started Successfully...!");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
