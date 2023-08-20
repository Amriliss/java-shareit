package ru.practicum.shareit.booking.service;

import ru.practicum.shareit.bookingDto.BookingDto;
import ru.practicum.shareit.bookingDto.BookingInfoDto;

import java.util.List;

public interface BookingService {

    BookingInfoDto create(Long userId, BookingDto bookingDto);

    BookingInfoDto approve(Long userId, Long bookingId, Boolean approved);

    BookingInfoDto get(Long userId, Long bookingId);

    List<BookingInfoDto> get(Long userId, String state, Integer from, Integer size);

    List<BookingInfoDto> getByOwner(Long userId, String state, Integer from, Integer size);
}
