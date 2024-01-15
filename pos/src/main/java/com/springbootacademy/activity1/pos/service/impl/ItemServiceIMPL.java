package com.springbootacademy.activity1.pos.service.impl;

import com.springbootacademy.activity1.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.activity1.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.activity1.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.activity1.pos.entity.Customer;
import com.springbootacademy.activity1.pos.entity.Item;
import com.springbootacademy.activity1.pos.exception.NotFoundException;
import com.springbootacademy.activity1.pos.repo.ItemRepo;
import com.springbootacademy.activity1.pos.service.ItemService;
import com.springbootacademy.activity1.pos.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        /*Item item = new Item(
                1,
                itemSaveRequestDTO.getItemName(),
                itemSaveRequestDTO.getMeasuringUnitType(),
                itemSaveRequestDTO.getBalanceQty(),
                itemSaveRequestDTO.getSupplierPrice(),
                itemSaveRequestDTO.getSellingPrice(),
                true
        );

        itemRepo.save(item);
        return item.getItemName();*/

        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        if(!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemId()+ " saved successfully";

        }else{
            throw new DuplicateKeyException("Already added");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,true);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item is not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusWithMapstruct(String itemName) {
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,true);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }else{
            throw new RuntimeException("Item is not active");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getAllItemsByStatus(boolean activeStatus) {
        List<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus);
        if(items.size()>0){
            List<ItemGetResponseDTO> itemGetResponseDTOS = itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }else{
            throw new NotFoundException("Item not found");
        }
    }

    @Override
    public PaginatedResponseItemDTO getAllItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> itemPage = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));

        if(itemPage.getSize()<1){
            throw new NotFoundException("No Data");
        }else{
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(itemPage),
                    itemRepo.countAllByActiveStateEquals(activeStatus)
            );

            return paginatedResponseItemDTO;
        }
    }
}
