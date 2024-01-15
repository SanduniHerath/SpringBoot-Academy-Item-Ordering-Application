package com.springbootacademy.activity1.pos.util.mappers;

import com.springbootacademy.activity1.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.activity1.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper (componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);

    List<ItemGetResponseDTO> ListDTOToPage(Page<Item> itemPage);

    //Page<Item> itemPage------>List<ItemGetResponseDTO>
}
