package lk.ijse.gdse66.service;

import lk.ijse.gdse66.dto.ItemDTO;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:24 AM - 6/18/2024
 **/
public interface ItemService {

    void saveItem(ItemDTO itemDTO);

    ItemDTO searchItem(String code);

    void updateItem(ItemDTO itemDTO);

    void deleteItem(String code);

    List<ItemDTO> loadAllItems();
}
