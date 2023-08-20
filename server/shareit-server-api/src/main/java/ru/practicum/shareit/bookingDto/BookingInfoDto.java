package ru.practicum.shareit.bookingDto;

import lombok.Builder;
import lombok.Data;

import ru.practicum.shareit.item.ItemInfoDto;
import ru.practicum.shareit.user.dto.UserInfoDto;

import java.time.LocalDateTime;

@Data
@Builder
public class BookingInfoDto {
    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private ItemInfoDto item;

    private UserInfoDto booker;

    private Status status;
}
