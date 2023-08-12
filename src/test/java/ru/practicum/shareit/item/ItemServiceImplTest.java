package ru.practicum.shareit.item;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.model.Comment;
import ru.practicum.shareit.comment.repository.CommentRepository;
import ru.practicum.shareit.exception.InvalidCommentException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.item.service.ItemServiceImpl;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class ItemServiceImplTest {

    @Autowired
    private final ItemServiceImpl itemService;

    @MockBean
    private final UserRepository userRepository;

    @MockBean
    private final ItemRepository itemRepository;

    @MockBean
    private final BookingRepository bookingRepository;

    @MockBean
    private final CommentRepository commentRepository;

    @Test
    void create() {
        User owner = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.com")
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(owner));

        ItemDto itemDto = ItemDto.builder()
                .name("name")
                .description("description")
                .available(true)
                .build();

        Item item = Item.builder()
                .id(1L)
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .owner(owner)
                .build();

        when(itemRepository.save(any()))
                .thenReturn(item);

        itemDto = itemService.create(1L, itemDto);

        assertThat(itemDto, is(notNullValue()));
    }

    @Test
    void throwUserNotFoundException() {
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        ItemDto itemDto = ItemDto.builder()
                .name("name")
                .description("description")
                .available(true)
                .build();

        NotFoundException invalidUserIdException;

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.create(1L, itemDto));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.update(1L, 1L, itemDto));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.get(1L, 1L));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.get(1L, 0, 10));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.search(1L, "text", 0, 10));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));

        CommentDto commentDto = CommentDto.builder()
                .text("comment")
                .build();

        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();
        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();
        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        when(userRepository.findById(3L))
                .thenReturn(Optional.empty());

        invalidUserIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.comment(3L, 1L, commentDto));
        assertThat(invalidUserIdException.getMessage(), is("user not found"));
    }

    @Test
    void throwItemNotFoundException() {
        User user = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.com")
                .build();

        User owner = User.builder()
                .id(2L)
                .name("name")
                .email("user2@email.com")
                .build();

        ItemDto itemDto = ItemDto.builder()
                .name("name")
                .description("description")
                .available(true)
                .build();

        NotFoundException invalidItemIdException;

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(itemRepository.findById(1L))
                .thenReturn(Optional.empty());

        invalidItemIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.update(1L, 1L, itemDto));
        assertThat(invalidItemIdException.getMessage(), is("item not found"));

        invalidItemIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.get(1L, 1L));
        assertThat(invalidItemIdException.getMessage(), is("item not found"));

        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();
        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        invalidItemIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.update(1L, 1L, itemDto));
        assertThat(invalidItemIdException.getMessage(), is("item not found"));

        CommentDto commentDto = CommentDto.builder()
                .text("comment")
                .build();
        when(itemRepository.findById(1L))
                .thenReturn(Optional.empty());

        invalidItemIdException = Assertions.assertThrows(NotFoundException.class,
                () -> itemService.comment(3L, 1L, commentDto));
        assertThat(invalidItemIdException.getMessage(), is("item not found"));
    }


    @Test
    void update() throws Exception {
        User owner = User.builder()
                .id(1L)
                .name("name")
                .email("user@email.com")
                .build();
        when(userRepository.findById(1L))
                .thenReturn(Optional.of(owner));

        ItemDto itemDto = ItemDto.builder()
                .name("nameUpdated")
                .description("descriptionUpdated")
                .build();

        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();
        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        Item itemUpdated = Item.builder()
                .id(1L)
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(true)
                .owner(owner)
                .build();
        when(itemRepository.save(any()))
                .thenReturn(itemUpdated);

        itemDto = itemService.update(1L, 1L, itemDto);
        assertThat(itemDto, is(notNullValue()));
    }

    @Test
    void getItem() throws Exception {
        User user = User.builder()
                .id(1L)
                .name("user1")
                .email("user1@email.com")
                .build();

        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();

        User booker = User.builder()
                .id(3L)
                .name("user3")
                .email("user3@email.com")
                .build();

        LocalDateTime created = LocalDateTime.now();
        Comment comment = Comment.builder()
                .id(1L)
                .text("text")
                .author(booker)
                .created(created)
                .build();
        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .comments(List.of(comment))
                .build();

        ItemDto itemDto;

        Booking lastBooking = Booking.builder()
                .id(1L)
                .start(created.minusMonths(5))
                .end(created.minusMonths(4))
                .item(item)
                .booker(booker)
                .status(Status.APPROVED)
                .build();

        Booking nextBooking = Booking.builder()
                .id(2L)
                .start(created.plusDays(1L))
                .end(created.plusDays(2L))
                .item(item)
                .booker(booker)
                .status(Status.APPROVED)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(owner));

        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        itemDto = itemService.get(1L, 1L);
        assertThat(itemDto, is(notNullValue()));

        when(bookingRepository.findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.empty());
        when(bookingRepository.findTop1BookingByItemIdAndStartIsAfterAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.empty());
        itemDto = itemService.get(2L, 1L);
        assertThat(itemDto.getLastBooking(), is(nullValue()));
        assertThat(itemDto.getNextBooking(), is(nullValue()));

        when(bookingRepository.findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.of(lastBooking));
        when(bookingRepository.findTop1BookingByItemIdAndStartIsAfterAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.of(nextBooking));
        itemDto = itemService.get(2L, 1L);
        assertThat(itemDto, is(notNullValue()));
    }

    @Test
    void getAll() throws Exception {
        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();

        User booker = User.builder()
                .id(3L)
                .name("user3")
                .email("user3@email.com")
                .build();

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(owner));

        when(itemRepository.findAllByOwnerId(any(), any()))
                .thenReturn(Collections.emptyList());

        List<ItemDto> itemDtos = itemService.get(2L, 0, 10);
        Assertions.assertTrue(itemDtos.isEmpty());

        LocalDateTime created = LocalDateTime.now();
        Comment comment = Comment.builder()
                .id(1L)
                .text("text")
                .author(booker)
                .created(created)
                .build();
        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .comments(List.of(comment))
                .build();

        List<Item> items = new ArrayList<>();
        items.add(item);

        when(itemRepository.findAllByOwnerId(any(), any()))
                .thenReturn(items);


        Booking lastBooking = Booking.builder()
                .id(1L)
                .start(created.minusMonths(5))
                .end(created.minusMonths(4))
                .item(item)
                .booker(booker)
                .status(Status.APPROVED)
                .build();

        Booking nextBooking = Booking.builder()
                .id(2L)
                .start(created.plusDays(1L))
                .end(created.plusDays(2L))
                .item(item)
                .booker(booker)
                .status(Status.APPROVED)
                .build();

        when(bookingRepository.findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.of(lastBooking));
        when(bookingRepository.findTop1BookingByItemIdAndStartIsAfterAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.of(nextBooking));

        itemDtos = itemService.get(2L, 0, 10);
        assertThat(itemDtos, is(notNullValue()));

        when(bookingRepository.findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.empty());
        when(bookingRepository.findTop1BookingByItemIdAndStartIsAfterAndStatusIs(any(), any(), any(), any()))
                .thenReturn(Optional.empty());

        itemDtos = itemService.get(2L, 0, 10);
        assertThat(itemDtos, is(notNullValue()));

        Item item2 = Item.builder()
                .id(2L)
                .name("name2")
                .description("description2")
                .available(true)
                .owner(owner)
                .build();

        items.add(item2);
        when(commentRepository.findAllByItemId(2L))
                .thenReturn(Collections.emptyList());

        itemDtos = itemService.get(2L, 0, 10);
        assertThat(itemDtos, is(notNullValue()));
    }
    @Test
    void search() throws Exception {
        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();

        when(userRepository.findById(2L))
                .thenReturn(Optional.of(owner));

        List<ItemDto> itemDtos = itemService.search(2L, "", 0, 10);
        Assertions.assertTrue(itemDtos.isEmpty());

        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();

        when(itemRepository.searchAvailableByText(any(), any()))
                .thenReturn(Collections.emptyList());

        itemDtos = itemService.search(2L, "text", 0, 10);
        Assertions.assertTrue(itemDtos.isEmpty());

        List<Item> items = List.of(item);

        when(itemRepository.searchAvailableByText(any(), any()))
                .thenReturn(items);
        itemDtos = itemService.search(2L, "description", 0, 10);
        assertThat(itemDtos, is(notNullValue()));
    }

    @Test
    void comment() throws Exception {
        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();

        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();

        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        User booker = User.builder()
                .id(3L)
                .name("user3")
                .email("user3@email.com")
                .build();
        when(userRepository.findById(3L))
                .thenReturn(Optional.of(booker));

        LocalDateTime created = LocalDateTime.now();

        Booking booking = Booking.builder()
                .id(1L)
                .start(created.minusMonths(5))
                .end(created.minusMonths(4))
                .item(item)
                .booker(booker)
                .status(Status.APPROVED)
                .build();
        when(bookingRepository
                .findTop1BookingByItemIdAndBookerIdAndEndIsBeforeAndStatusIs(any(), any(), any(), any(), any())
        ).thenReturn(Optional.of(booking));

        Comment comment = Comment.builder()
                .id(1L)
                .text("text")
                .item(item)
                .author(booker)
                .created(created)
                .build();
        when(commentRepository.save(any()))
                .thenReturn(comment);

        CommentDto commentDto = CommentDto.builder()
                .text("comment")
                .build();
        commentDto = itemService.comment(3L, 1L, commentDto);
        assertThat(commentDto, is(notNullValue()));
    }

    @Test
    void throwInvalidCommentException() {
        User owner = User.builder()
                .id(2L)
                .name("user2")
                .email("user2@email.com")
                .build();

        Item item = Item.builder()
                .id(1L)
                .name("name")
                .description("description")
                .available(true)
                .owner(owner)
                .build();

        when(itemRepository.findById(1L))
                .thenReturn(Optional.of(item));

        User booker = User.builder()
                .id(3L)
                .name("user3")
                .email("user3@email.com")
                .build();
        when(userRepository.findById(3L))
                .thenReturn(Optional.of(booker));

        LocalDateTime created = LocalDateTime.now();

        when(bookingRepository
                .findTop1BookingByItemIdAndBookerIdAndEndIsBeforeAndStatusIs(any(), any(), any(), any(), any())
        ).thenReturn(Optional.empty());

        CommentDto commentDto = CommentDto.builder()
                .text("comment")
                .build();

        InvalidCommentException invalidCommentException;

        invalidCommentException = Assertions.assertThrows(InvalidCommentException.class,
                () -> itemService.comment(3L, 1L, commentDto));
        assertThat(invalidCommentException.getMessage(), is("no booking for comment"));
    }
}
