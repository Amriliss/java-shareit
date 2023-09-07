package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.Marker;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;



@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemGateway {

    private static final String X_SHARER_USER_ID = "X-Sharer-User-Id";

    private final ItemClient itemClient;
    //private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                         @Validated(Marker.OnCreate.class) @RequestBody ItemDto itemDto) {
        log.info("Добавление вещи", userId);
        return itemClient.create(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long itemId, @RequestBody ItemDto itemDto) {
        log.info("Обновление данных вещи");
        return itemClient.update(userId, itemId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> get(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long itemId) {
        log.info("Получение вещи по id {}", itemId);
        return itemClient.get(userId, itemId);
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                      @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                      @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получение всех вещей");
        return itemClient.get(userId, from, size);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                         @RequestParam String text,
                                         @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                         @RequestParam(defaultValue = "10") @Positive Integer size
    ) {
        log.info("Поиск вещи");
        return itemClient.search(userId, text, from, size);
    }

    @PostMapping("/{itemId}/comment")
    public  ResponseEntity<Object> comment(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                           @PathVariable Long itemId,
                                           @Valid @RequestBody CommentDto commentDto) {
        log.info("Добавление комментария");
        return itemClient.comment(userId, itemId, commentDto);
    }

}
