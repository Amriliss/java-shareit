//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.practicum.shareit.requests.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@Component
public class ItemRequestMapperImpl implements ItemRequestMapper {
    public ItemRequestMapperImpl() {
    }

    public ItemRequestDto toItemRequestDto(ItemRequest itemRequest) {
        if (itemRequest == null) {
            return null;
        } else {
            ItemRequestDto.ItemRequestDtoBuilder itemRequestDto = ItemRequestDto.builder();
            itemRequestDto.id(itemRequest.getId());
            itemRequestDto.requester(this.itemRequestRequesterId(itemRequest));
            itemRequestDto.description(itemRequest.getDescription());
            itemRequestDto.created(itemRequest.getCreated());
            itemRequestDto.items(this.setItems());
            return itemRequestDto.build();
        }
    }

    public ItemRequest toItemRequest(ItemRequestDto itemRequestDto, User user) {
        if (itemRequestDto == null && user == null) {
            return null;
        } else {
            ItemRequest.ItemRequestBuilder itemRequest = ItemRequest.builder();
            if (itemRequestDto != null) {
                itemRequest.id(itemRequestDto.getId());
                itemRequest.description(itemRequestDto.getDescription());
                itemRequest.created(itemRequestDto.getCreated());
            }

            itemRequest.requester(user);
            return itemRequest.build();
        }
    }

    private Long itemRequestRequesterId(ItemRequest itemRequest) {
        if (itemRequest == null) {
            return null;
        } else {
            User requester = itemRequest.getRequester();
            if (requester == null) {
                return null;
            } else {
                Long id = requester.getId();
                return id == null ? null : id;
            }
        }
    }
}
