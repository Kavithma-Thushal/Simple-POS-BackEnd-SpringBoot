package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.service.ItemService;
import lk.ijse.gdse66.pos.util.ResponseUtil;
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
    public ResponseEntity<ResponseUtil<String>> saveItem(@Valid @RequestBody ItemDTO itemDTO) {
        ResponseUtil<String> responseUtil = itemService.saveItem(itemDTO);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/searchItem/{code}")
    public ResponseEntity<ResponseUtil<ItemDTO>> searchItem(@PathVariable String code) {
        ResponseUtil<ItemDTO> responseUtil = itemService.searchItem(code);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @PutMapping("/updateItem")
    public ResponseEntity<ResponseUtil<String>> updateItem(@Valid @RequestBody ItemDTO itemDTO) {
        ResponseUtil<String> responseUtil = itemService.updateItem(itemDTO);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @DeleteMapping("/deleteItem/{code}")
    public ResponseEntity<ResponseUtil<String>> deleteItem(@PathVariable String code) {
        ResponseUtil<String> responseUtil = itemService.deleteItem(code);
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }

    @GetMapping("/loadAllItems")
    public ResponseEntity<ResponseUtil<List<ItemDTO>>> loadAllItems() {
        ResponseUtil<List<ItemDTO>> responseUtil = itemService.loadAllItems();
        return new ResponseEntity<>(responseUtil, responseUtil.getStatus());
    }
}