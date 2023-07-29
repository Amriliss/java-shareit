package ru.practicum.shareit.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        log.info("Добавление пользователя");
        return userService.create(userDto);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("Обновление данных пользователя");
        return userService.update(userId, userDto);
    }

    @GetMapping
    public List<UserDto> get() {
        log.info("Получение всех пользователей");
        return userService.get();
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable Long userId) {
        log.info("Получение пользователя по id {}", userId);
        return userService.get(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        log.info("Удаление пользователя по id {}", userId);
        userService.delete(userId);
    }
}