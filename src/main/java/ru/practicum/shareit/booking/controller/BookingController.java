package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
public class BookingController {
    private final String REQUEST_HEADER = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @Transactional
    @PostMapping
    public BookingInfoDto create(@RequestHeader(REQUEST_HEADER) Long userId, @Valid @RequestBody BookingDto bookingDto) {
        log.info("Добавление брони", userId);
        return bookingService.create(userId, bookingDto);
    }

    @Transactional
    @PatchMapping("/{bookingId}")
    public BookingInfoDto approve(@RequestHeader(REQUEST_HEADER) Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved
    ) {
        log.info("Подтверждение брони", userId);
        return bookingService.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingInfoDto get(@RequestHeader(REQUEST_HEADER) Long userId, @PathVariable Long bookingId) {
        log.info("Получение брони", userId);
        return bookingService.get(userId, bookingId);
    }

    @GetMapping
    public List<BookingInfoDto> get(@RequestHeader(REQUEST_HEADER) Long userId,
                                    @RequestParam(defaultValue = "ALL", required = false) String state) {
        log.info("Получение всей брони");
        return bookingService.get(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingInfoDto> getByOwner(@RequestHeader(REQUEST_HEADER) Long userId,
                                           @RequestParam(defaultValue = "ALL", required = false) String state) {
        log.info("Получение брони по владельцу", userId);
        return bookingService.getByOwner(userId, state);
    }
}
