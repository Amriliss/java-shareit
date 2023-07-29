package ru.practicum.shareit.booking.dto;

public enum BookingState {

        ALL,
        CURRENT,
        PAST,
        FUTURE,
        WAITING,
        REJECTED;

        public static BookingState validateState(String value) throws IllegalStateException  {
            try {
                return BookingState.valueOf(value);
            } catch (Exception exception) {
                throw new IllegalStateException("Unknown state: " + value);
            }
        }
    }
