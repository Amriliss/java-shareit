package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.user.dto.Marker;
import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto createUser(@RequestBody @Validated(Marker.OnCreate.class) UserDto userDto) {
        log.info("Добавление пользователя");
        return userService.create(userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody @Validated(Marker.OnUpdate.class) UserDto userDto) {
        log.info("Обновление данных пользователя");
        return userService.update(id, userDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("Получение всех пользователей");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        log.info("Получение пользователя по id {}", id);
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        log.info("Удаление пользователя по id {}", id);
        userService.delete(id);
    }

}
