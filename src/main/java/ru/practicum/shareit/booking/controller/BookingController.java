package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInfoDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j

public class BookingController {
    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @PostMapping
    public BookingInfoDto create(@RequestHeader(X_SHARER_USER_ID) Long userId, @Valid @RequestBody BookingDto bookingDto) {
        log.info("Добавление брони", userId);
        return bookingService.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingInfoDto approve(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved
    ) {
        log.info("Подтверждение брони", userId);
        return bookingService.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingInfoDto get(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long bookingId) {
        log.info("Получение брони", userId);
        return bookingService.get(userId, bookingId);
    }

    @GetMapping
    public List<BookingInfoDto> get(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                    @RequestParam(defaultValue = "ALL", required = false) String state,
                                    @RequestParam(defaultValue = "0") Long from,
                                    @RequestParam(defaultValue = "10") Long size) {
        log.info("Получение всей брони");
        return bookingService.get(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<BookingInfoDto> getByOwner(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                           @RequestParam(defaultValue = "ALL", required = false) String state,
                                           @RequestParam(defaultValue = "0") Long from,
                                           @RequestParam(defaultValue = "10") Long size) {
        log.info("Получение брони по владельцу", userId);
        return bookingService.getByOwner(userId, state, from, size);
    }
}
