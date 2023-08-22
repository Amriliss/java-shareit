package ru.practicum.shareit.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserDto {

    private Long id;

    @Pattern(regexp = "\\S*$")
    @NotBlank(groups = {Marker.OnCreate.class})
    private String name;

    @Email(groups = {Marker.OnCreate.class, Marker.OnUpdate.class}, message = "Email не верный")
    @NotEmpty(groups = {Marker.OnCreate.class}, message = "Email не может быть пустым")
    @NotBlank
    private String email;
}