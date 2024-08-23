package lk.ijse.gdse66.pos.service.impl;

import lk.ijse.gdse66.pos.dto.ItemDTO;
import lk.ijse.gdse66.pos.entity.Item;
import lk.ijse.gdse66.pos.repo.ItemRepo;
import lk.ijse.gdse66.pos.service.ItemService;
import lk.ijse.gdse66.pos.util.EmailSender;
import lk.ijse.gdse66.pos.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmailSender emailSender;

    @Override
    public Response<String> saveItem(ItemDTO itemDTO) {

        if (!itemRepo.existsById(itemDTO.getCode())) {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));

            String successResponse = "Item Saved Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Item Management", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Item Already Exists...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public Response<ItemDTO> searchItem(String id) {

        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {
            ItemDTO itemDTO = modelMapper.map(item.get(), ItemDTO.class);

            String successResponse = "Item Searched Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Item Management", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, itemDTO);

        } else {
            String errorResponse = "Item Not Found...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public Response<String> updateItem(ItemDTO itemDTO) {

        if (itemRepo.existsById(itemDTO.getCode())) {
            itemRepo.save(modelMapper.map(itemDTO, Item.class));

            String successResponse = "Item Updated Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Item Management", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Item Not Found...!";
            log.info("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.BAD_REQUEST, null);
        }
    }

    @Override
    public Response<String> deleteItem(String id) {

        Optional<Item> item = itemRepo.findById(id);
        if (item.isPresent()) {

            itemRepo.deleteById(id);
            String successResponse = "Item Deleted Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Item Management", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, null);

        } else {
            String errorResponse = "Item Not Found...!";
            log.error("\u001B[31m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NOT_FOUND, null);
        }
    }

    @Override
    public Response<List<ItemDTO>> loadAllItems() {

        List<Item> itemList = itemRepo.findAll();
        List<ItemDTO> itemDTOList = itemList.stream()
                .map(item -> modelMapper.map(item, ItemDTO.class))
                .collect(Collectors.toList());

        if (!itemDTOList.isEmpty()) {
            String successResponse = "Item Loaded Successfully...!";
            log.info("\u001B[34m{}\u001B[0m", successResponse);
            emailSender.sendEmail("kavithmathushal451@gmail.com", "Item Management", successResponse);
            return new Response<>(successResponse, HttpStatus.OK, itemDTOList);

        } else {
            String errorResponse = "Items Not Found...!";
            log.info("\u001B[33m{}\u001B[0m", errorResponse);
            return new Response<>(errorResponse, HttpStatus.NO_CONTENT, null);
        }
    }
}