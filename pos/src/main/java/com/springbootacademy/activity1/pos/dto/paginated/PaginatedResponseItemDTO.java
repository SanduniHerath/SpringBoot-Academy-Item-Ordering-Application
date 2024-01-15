package com.springbootacademy.activity1.pos.dto.paginated;

import com.springbootacademy.activity1.pos.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    List<ItemGetResponseDTO> list;
    private long dataCount;

}
