package ru.practicum.shareit.strategy.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.strategy.BookingStateFetchStrategyForBooker;
import ru.practicum.shareit.strategy.StrategyName;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class StrategyBookerCurrent implements BookingStateFetchStrategyForBooker {

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> fetch(Long userId, PageRequest pageRequest) {
        return bookingRepository
                .findAllByBookerIdAndStartIsBeforeAndEndIsAfter(
                        userId, LocalDateTime.now(), LocalDateTime.now(), pageRequest);
    }

    @Override
    public StrategyName getStrategy() {
        return StrategyName.CURRENT;
    }
}
