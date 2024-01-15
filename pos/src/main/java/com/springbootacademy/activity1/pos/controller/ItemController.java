package com.springbootacademy.activity1.pos.controller;

import com.springbootacademy.activity1.pos.dto.CustomerDTO;
import com.springbootacademy.activity1.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.activity1.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.activity1.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.activity1.pos.service.ItemService;
import com.springbootacademy.activity1.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*@PostMapping(
            path = {"/save"}
    )
    public String saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);
        return message;

    }*/

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO){
        String message = itemService.saveItem(itemSaveRequestDTO);
        //ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
         //       new StandardResponse(201,"Success",message), HttpStatus.CREATED
        //);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message),
                HttpStatus.CREATED
        );

    }

    @GetMapping(
            path = {"/get-by-name"},
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;
    }

    @GetMapping(
            path = {"/get-by-name-with-mapstruct"},
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatusWithMapstruct(@RequestParam(value = "name") String itemName){
        List<ItemGetResponseDTO> itemDTOS = itemService.getItemByNameAndStatusWithMapstruct(itemName);
        return itemDTOS;
    }

    @GetMapping(
            path = {"/get-all-items-by-activestatus"},
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getAllItemsByStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")  int size){
        //List<ItemGetResponseDTO> itemDTOS = itemService.getAllItemsByStatus(activeStatus);
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsByActiveStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }
}
