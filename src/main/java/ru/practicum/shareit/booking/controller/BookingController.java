package ru.practicum.shareit.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInfoDto;
import ru.practicum.shareit.booking.service.BookingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final String xSharerUserId = "X-Sharer-User-Id";
    private final BookingService bookingService;

    @PostMapping
    public BookingInfoDto create(@RequestHeader(xSharerUserId) Long userId, @Valid @RequestBody BookingDto bookingDto) {
        return bookingService.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public BookingInfoDto approve(@RequestHeader(xSharerUserId) Long userId, @PathVariable Long bookingId,
                                  @RequestParam Boolean approved
    ) {
        return bookingService.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public BookingInfoDto get(@RequestHeader(xSharerUserId) Long userId, @PathVariable Long bookingId) {
        return bookingService.get(userId, bookingId);
    }

    @GetMapping
    public List<BookingInfoDto> get(@RequestHeader(xSharerUserId) Long userId,
                                    @RequestParam(defaultValue = "ALL", required = false) String state) {
        return bookingService.get(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingInfoDto> getByOwner(@RequestHeader(xSharerUserId) Long userId,
                                           @RequestParam(defaultValue = "ALL", required = false) String state) {
        return bookingService.getByOwner(userId, state);
    }
}
