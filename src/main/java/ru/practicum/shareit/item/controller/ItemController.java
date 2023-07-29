package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.service.CommentService;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final String X_SHARER_USER_ID = "X-Sharer-User-Id";
    private final ItemService itemService;
    private final CommentService commentService;

    @PostMapping
    public ItemDto create(@RequestHeader(X_SHARER_USER_ID) Long userId, @Valid @RequestBody ItemDto itemDto) {
        log.info("Добавление вещи", userId);
        return itemService.create(userId, itemDto);
    }
    @PatchMapping("/{itemId}")
    public ItemDto update(
            @RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long itemId, @RequestBody ItemDto itemDto) {
        log.info("Обновление данных вещи");
        return itemService.update(userId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemDto get(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long itemId) {
        log.info("Получение вещи по id {}", itemId);

        return itemService.get(userId, itemId);
    }
    @GetMapping
    public List<ItemDto> get(@RequestHeader(X_SHARER_USER_ID) Long userId) {
        log.info("Получение всех вещей");
        return itemService.get(userId);
    }


    @GetMapping("/search")
    public List<ItemDto> search(@RequestHeader(X_SHARER_USER_ID) Long userId, @RequestParam String text) {
        log.info("Поиск вещи");
        return itemService.search(userId, text);
    }
    @PostMapping("/{itemId}/comment")
    public CommentDto comment(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long itemId,
                              @Valid @RequestBody CommentDto commentDto) {
        log.info("Добавление комментария");
        return commentService.comment(userId, itemId, commentDto);
    }

}
