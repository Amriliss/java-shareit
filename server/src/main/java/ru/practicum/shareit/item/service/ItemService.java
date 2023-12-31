package ru.practicum.shareit.item.service;

import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {

    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto update(Long userId, Long itemId, ItemDto itemDto);

    ItemDto get(Long userId, Long itemId);

    List<ItemDto> get(Long userId, Integer from, Integer size);

    List<ItemDto> search(Long userId, String text, Integer from, Integer size);

    CommentDto comment(Long userId, Long itemId, CommentDto commentDto);
}