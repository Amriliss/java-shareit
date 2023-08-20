package ru.practicum.shareit.shareit.booking;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.bookingDto.BookingDto;
import ru.practicum.shareit.bookingDto.State;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingGateway {

    public static final String X_SHARER_USER_ID = "X-Sharer-User-Id";

    private final BookingClient bookingClient;

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                         @Valid @RequestBody BookingDto bookingDto) {
        log.info("Добавление брони", userId);
        return bookingClient.create(userId, bookingDto);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Object> approve(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                          @PathVariable Long bookingId,
                                          @RequestParam Boolean approved
    ) {
        log.info("Подтверждение брони", userId);
        return bookingClient.approve(userId, bookingId, approved);
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Object> get(@RequestHeader(X_SHARER_USER_ID) Long userId, @PathVariable Long bookingId) {
        log.info("Получение брони", userId);
        return bookingClient.get(userId, bookingId);
    }

    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                    @RequestParam(defaultValue = "ALL", required = false) String state,
                                    @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                    @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получение всей брони");
        return bookingClient.get(userId, state, from, size);
    }

    @GetMapping("/owner")
    public ResponseEntity<Object> getByOwner(@RequestHeader(X_SHARER_USER_ID) Long userId,
                                           @RequestParam(defaultValue = "ALL", required = false) String state,
                                           @RequestParam(defaultValue = "0") @PositiveOrZero Integer from,
                                           @RequestParam(defaultValue = "10") @Positive Integer size) {
        log.info("Получение брони по владельцу", userId);
        return bookingClient.getByOwner(userId, state, from, size);
    }
}



