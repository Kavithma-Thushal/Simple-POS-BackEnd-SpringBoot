package lk.ijse.gdse66.service.impl;

import lk.ijse.gdse66.dto.ItemDTO;
import lk.ijse.gdse66.entity.Item;
import lk.ijse.gdse66.repo.ItemRepo;
import lk.ijse.gdse66.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:25 AM - 6/18/2024
 **/
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        if (!itemRepo.existsById(itemDTO.getCode())) {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
        }
    }

    @Override
    public ItemDTO searchItem(String code) {
        Optional<Item> deleteCode = itemRepo.findById(code);
        return deleteCode.map(item -> modelMapper.map(item, ItemDTO.class)).orElse(null);
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getCode())) {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
        }
    }

    @Override
    public void deleteItem(String code) {
        itemRepo.deleteById(code);
    }

    @Override
    public List<ItemDTO> loadAllItems() {
        List<Item> itemList = itemRepo.findAll();
        return itemList.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());
    }
}
