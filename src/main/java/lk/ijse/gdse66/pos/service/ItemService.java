package lk.ijse.gdse66.pos.service;

import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.util.ResponseUtil;

import java.util.List;

/**
 * @author : Kavithma Thushal
 * @project : Simple-POS-BackEnd-SpringBoot
 * @since : 7:24 AM - 6/18/2024
 **/
public interface ItemService {

    ResponseUtil<String> saveItem(ItemDTO itemDTO);

    ResponseUtil<ItemDTO> searchItem(String code);

    ResponseUtil<String> updateItem(ItemDTO itemDTO);

    ResponseUtil<String> deleteItem(String code);

    ResponseUtil<List<ItemDTO>> loadAllItems();

    ResponseUtil<String> generateItemCode();

    ResponseUtil<Integer> getItemCount();
}