package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.Marker;
import ru.practicum.shareit.user.dto.UserDto;


@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserGateway {
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> create(@Validated(Marker.OnCreate.class) @RequestBody UserDto userDto) {
        log.info("Добавление пользователя");
        return userClient.create(userDto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable Long userId, @RequestBody UserDto userDto) {
        log.info("Обновление данных пользователя");
        return userClient.update(userId, userDto);
    }

    @GetMapping
    public ResponseEntity<Object> get() {
        log.info("Получение всех пользователей");
        return userClient.get();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> get(@PathVariable Long userId) {
        log.info("Получение пользователя по id {}", userId);
        return userClient.get(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        log.info("Удаление пользователя по id {}", userId);
        userClient.delete(userId);
    }
}
