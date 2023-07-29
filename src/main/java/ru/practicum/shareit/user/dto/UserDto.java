package ru.practicum.shareit.user.dto;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
public class UserDto {

    private Long id;

    @Pattern(regexp = "\\S*$")
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
}