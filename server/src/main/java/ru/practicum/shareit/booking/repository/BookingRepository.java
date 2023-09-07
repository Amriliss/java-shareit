package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByBookerId(Long userId, Pageable pageable); //all by booker id

    List<Booking> findAllByBookerIdAndStartIsAfter(Long userId, LocalDateTime start, PageRequest pageRequest); //future by booker id

    List<Booking> findAllByBookerIdAndStartIsBeforeAndEndIsAfter(
            Long userId, LocalDateTime start, LocalDateTime end, PageRequest pageRequest); //current by booker id

    List<Booking> findAllByBookerIdAndEndIsBefore(Long userId, LocalDateTime start, PageRequest pageRequest); //past by booker id

    List<Booking> findAllByBookerIdAndStatus(Long userId, Status status, PageRequest pageRequest); //by status and booker id

    List<Booking> findAllByItemOwnerId(Long userId, PageRequest pageRequest); // all by owner id

    List<Booking> findAllByItemOwnerIdAndStartIsAfter(
            Long userId, LocalDateTime start, PageRequest pageRequest); // future by owner id

    List<Booking> findAllByItemOwnerIdAndStartIsBeforeAndEndIsAfter(
            Long userId, LocalDateTime start, LocalDateTime end, PageRequest pageRequest); //current by owner id

    List<Booking> findAllByItemOwnerIdAndEndIsBefore(Long userId, LocalDateTime start, PageRequest pageRequest); //past by owner id

    List<Booking> findAllByItemOwnerIdAndStatus(Long userId, Status status, PageRequest pageRequest); //by status and owner id

    Optional<Booking> findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(
            Long itemId, LocalDateTime start, Status status, Sort sort);

    Optional<Booking> findTop1BookingByItemIdAndStartIsAfterAndStatusIs(
            Long itemId, LocalDateTime start, Status status, Sort sort);

    Optional<Booking> findTop1BookingByItemIdAndBookerIdAndEndIsBeforeAndStatusIs(
            Long itemId, Long bookerId, LocalDateTime end, Status status, Sort sort);

}
