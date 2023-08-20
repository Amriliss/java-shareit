package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.service.BookingService;
import ru.practicum.shareit.bookingDto.BookingDto;
import ru.practicum.shareit.bookingDto.BookingInfoDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {

    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";

    private final BookingService bookingService;

    @PostMapping
    public BookingInfoDto create(@RequestHeader(X_SHARER_USER_ID) Long userId, @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingInfoDto approve(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved
    ) {
        return bookingService.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingInfoDto get(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long bookingId) {
        return bookingService.get(userId, bookingId);
    }

    @GetMapping
    public List<BookingInfoDto> get(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                    @RequestParam(defaultValue = "ALL", required = false) String state,
                                    @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                    @RequestParam(defaultValue = "10") @Positive Integer size) {
        return bookingService.get(userId, state, from, size);
    }

    @GetMapping("/owner")
    public List<BookingInfoDto> getByOwner(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                           @RequestParam(defaultValue = "ALL", required = false) String state,
                                           @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                           @RequestParam(defaultValue = "10") @Positive Integer size) {
        return bookingService.getByOwner(userId, state, from, size);
    }
}
