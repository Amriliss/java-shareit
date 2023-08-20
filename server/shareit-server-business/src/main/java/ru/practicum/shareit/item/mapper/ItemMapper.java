package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.comment.mapper.CommentMapper;
import ru.practicum.shareit.item.CommentDto;
import ru.practicum.shareit.item.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.requests.model.ItemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemMapper {

    public static ItemDto toItemDto(Item item) {
        List<CommentDto> comments = getCommentDtos(item);
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .owner(item.getOwner().getId())
                .description(item.getDescription())
                .available(item.getAvailable())
                .requestId(Optional.ofNullable(item.getRequest()).map(ItemRequest::getId).orElse(null))
                .lastBooking(null)
                .nextBooking(null)
                .comments(comments)
                .build();
    }

    public static Item toItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .request(itemDto.getRequestId() == null ?
                        null : ItemRequest.builder().id(itemDto.getRequestId()).build())
                .build();
    }

    public static Item matchItem(ItemDto itemDto, Item item) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName() == null ? item.getName() : itemDto.getName())
                .description(itemDto.getDescription() == null ? item.getDescription() : itemDto.getDescription())
                .available(itemDto.getAvailable() == null ? item.getAvailable() : itemDto.getAvailable())
                .request(itemDto.getRequestId() == null ?
                        item.getRequest() : ItemRequest.builder().id(itemDto.getRequestId()).build())
                .build();
    }

    private static List<CommentDto> getCommentDtos(Item item) {
        if (item.getComments() == null) {
            return new ArrayList<>();
        }
        return item.getComments()
                .stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toList());
    }
}