package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.CustomerDTO;
import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.service.ItemService;
import lk.ijse.gdse66.pos.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/saveItem")
    public ResponseEntity<Response<String>> saveItem(@Valid @RequestBody ItemDTO itemDTO) {
        Response<String> response = itemService.saveItem(itemDTO);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/searchItem/{code}")
    public ResponseEntity<Response<ItemDTO>> searchItem(@PathVariable String code) {
        Response<ItemDTO> response = itemService.searchItem(code);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/updateItem")
    public ResponseEntity<Response<String>> updateItem(@Valid @RequestBody ItemDTO itemDTO) {
        Response<String> response = itemService.updateItem(itemDTO);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/deleteItem/{code}")
    public ResponseEntity<Response<String>> deleteItem(@PathVariable String code) {
        Response<String> response = itemService.deleteItem(code);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/loadAllItems")
    public ResponseEntity<Response<List<ItemDTO>>> loadAllItems() {
        Response<List<ItemDTO>> response = itemService.loadAllItems();
        return new ResponseEntity<>(response, response.getStatus());
    }
}