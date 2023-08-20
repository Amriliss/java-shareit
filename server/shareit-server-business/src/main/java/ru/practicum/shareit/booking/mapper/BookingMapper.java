package ru.practicum.shareit.booking.mapper;


import lombok.experimental.UtilityClass;

import ru.practicum.shareit.booking.model.Booking;

import ru.practicum.shareit.bookingDto.BookingDto;
import ru.practicum.shareit.bookingDto.BookingInfoDto;
import ru.practicum.shareit.item.ItemInfoDto;
import ru.practicum.shareit.user.dto.UserInfoDto;

@UtilityClass
public class BookingMapper {

    public static BookingInfoDto toBookingInfoDto(Booking booking) {
        return BookingInfoDto.builder()
                .id(booking.getId())
                .start(booking.getStart())
                .end(booking.getEnd())
                .item(ItemInfoDto.builder()
                        .id(booking.getItem().getId())
                        .name(booking.getItem().getName())
                        .build())
                .booker(UserInfoDto.builder()
                        .id(booking.getBooker().getId())
                        .build())
                .status(booking.getStatus())
                .build();
    }

    public static Booking toBooking(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .start(bookingDto.getStart())
                .end(bookingDto.getEnd())
                .build();
    }
}
