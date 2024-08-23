package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.util.Response;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:24 AM - 6/18/2024
 **/
public interface ItemService {

    Response<String> saveItem(ItemDTO itemDTO);

    Response<ItemDTO> searchItem(String code);

    Response<String> updateItem(ItemDTO itemDTO);

    Response<String> deleteItem(String code);

    Response<List<ItemDTO>> loadAllItems();
}