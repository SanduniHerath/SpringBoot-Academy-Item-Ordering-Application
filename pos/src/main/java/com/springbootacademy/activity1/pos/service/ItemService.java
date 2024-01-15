package com.springbootacademy.activity1.pos.service;

import com.springbootacademy.activity1.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.activity1.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.activity1.pos.dto.response.ItemGetResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusWithMapstruct(String itemName);

    List<ItemGetResponseDTO> getAllItemsByStatus(boolean activeStatus);

    PaginatedResponseItemDTO getAllItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size);
}
