package ru.practicum.shareit.booking.repository;

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

    List<Booking> findAllByBookerIdOrderByStartDesc(Long userId); // all by booker id

    List<Booking> findAllByBookerIdAndStartIsAfter(Long userId, LocalDateTime start, Sort sort); // future by booker id

    List<Booking> findAllByBookerIdAndStartIsBeforeAndEndIsAfter(
            Long userId, LocalDateTime start, LocalDateTime end, Sort sort); // current by booker id

    List<Booking> findAllByBookerIdAndEndIsBefore(Long userId, LocalDateTime start, Sort sort); // past by booker id

    List<Booking> findAllByBookerIdAndStatus(Long userId, Status status); // by status and booker id

    List<Booking> findAllByItemOwnerIdOrderByStartDesc(Long userId); // all by owner id

    List<Booking> findAllByItemOwnerIdAndStartIsAfter(
            Long userId, LocalDateTime start, Sort sort); // future by owner id

    List<Booking> findAllByItemOwnerIdAndStartIsBeforeAndEndIsAfter(
            Long userId, LocalDateTime start, LocalDateTime end, Sort sort); // current by owner id

    List<Booking> findAllByItemOwnerIdAndEndIsBefore(Long userId, LocalDateTime start, Sort sort); // past by owner id

    List<Booking> findAllByItemOwnerIdAndStatus(Long userId, Status status); // by status and owner id

    Optional<Booking> findTop1BookingByItemIdAndStartIsBeforeAndStatusIs(
            Long itemId, LocalDateTime start, Status status, Sort sort);

    Optional<Booking> findTop1BookingByItemIdAndStartIsAfterAndStatusIs(
            Long itemId, LocalDateTime start, Status status, Sort sort);

    Optional<Booking> findTop1BookingByItemIdAndBookerIdAndEndIsBeforeAndStatusIs(
            Long itemId, Long bookerId, LocalDateTime end, Status status, Sort sort);

}
