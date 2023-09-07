package ru.practicum.shareit.requests.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.requests.dto.ItemRequestDto;
import ru.practicum.shareit.requests.service.ItemRequestService;


import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
@Validated
public class ItemRequestController {

    private static final String X_SHARER_USER_ID = "X-Sharer-User-Id";

    private final ItemRequestService itemRequestService;

    @PostMapping
    public ItemRequestDto create(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                 @RequestBody ItemRequestDto itemRequestDto) {
        return itemRequestService.create(userId, itemRequestDto);
    }

    @GetMapping
    public List<ItemRequestDto> get(@RequestHeader(X_SHARER_USER_ID) Long userId) {
        return itemRequestService.get(userId);
    }

    @GetMapping("/all")
    public List<ItemRequestDto> get(
            @RequestHeader(X_SHARER_USER_ID) Long userId,
            @RequestParam(defaultValue = "0")  Integer from,
            @RequestParam(defaultValue = "10")  Integer size
    ) {
        return itemRequestService.get(userId, from, size);
    }

    @GetMapping("/{requestId}")
    public ItemRequestDto get(@RequestHeader(X_SHARER_USER_ID) Long userId,
                              @PathVariable Long requestId) {
        return itemRequestService.get(userId, requestId);
    }
}
