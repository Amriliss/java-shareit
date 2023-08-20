package ru.practicum.shareit.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class ErrorResponse {
    private String error;
    private String description;
}

