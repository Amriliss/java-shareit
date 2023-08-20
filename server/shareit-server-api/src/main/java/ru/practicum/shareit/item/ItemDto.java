package ru.practicum.shareit.item;

import lombok.*;
import ru.practicum.shareit.bookingDto.LastBookingDto;
import ru.practicum.shareit.bookingDto.NextBookingDto;
import ru.practicum.shareit.user.dto.Marker;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * TODO Sprint add-controllers.
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ItemDto {

    private Long id;

    @NotBlank(groups = {Marker.OnCreate.class})
    private String name;

    @NotBlank(groups = {Marker.OnCreate.class})
    private String description;

    @NotNull(groups = {Marker.OnCreate.class})

    private Boolean available;

    private Long owner;

    private Long requestId;

    private LastBookingDto lastBooking;

    private NextBookingDto nextBooking;

    private List<CommentDto> comments;
}
