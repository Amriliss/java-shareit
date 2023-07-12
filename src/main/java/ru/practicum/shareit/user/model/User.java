package ru.practicum.shareit.user.model;

import lombok.*;

/**
 * TODO Sprint add-controllers.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String name;

}