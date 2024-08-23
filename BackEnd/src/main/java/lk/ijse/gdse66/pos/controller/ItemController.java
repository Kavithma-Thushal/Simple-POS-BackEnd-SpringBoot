package lk.ijse.gdse66.pos.controller;

import jakarta.validation.Valid;
import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveItem(@Valid @RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
    }

    @GetMapping("/searchItem/{code}")
    public ItemDTO searchItem(@PathVariable String code) {
        return itemService.searchItem(code);
    }

    @PutMapping("/updateItem")
    public void updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
    }

    @DeleteMapping("/deleteItem/{code}")
    public void deleteItem(@PathVariable String code) {
        itemService.deleteItem(code);
    }

    @GetMapping("/loadAllItems")
    public List<ItemDTO> loadAllItems() {
        return itemService.loadAllItems();
    }
}
