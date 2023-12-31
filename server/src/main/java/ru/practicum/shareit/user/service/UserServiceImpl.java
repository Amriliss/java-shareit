package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto update(Long userId, UserDto userDto) {
        userDto.setId(userId);
        User repoUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        User user = UserMapper.matchUser(userDto, repoUser);
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto get(Long userId) {
        User repoUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user not found"));
        return userMapper.toUserDto(repoUser);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> get() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }
}
