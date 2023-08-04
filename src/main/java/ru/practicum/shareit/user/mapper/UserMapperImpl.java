package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserInfoDto;
import ru.practicum.shareit.user.model.User;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2023-07-28T18:19:28+0300",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserInfoDto toUserInfoDto(User user) {
        if (user == null) {
            return null;
        }

        UserInfoDto.UserInfoDtoBuilder userInfoDto = UserInfoDto.builder();

        userInfoDto.id(user.getId());

        return userInfoDto.build();
    }

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id(user.getId());
        userDto.name(user.getName());
        userDto.email(user.getEmail());

        return userDto.build();
    }

    @Override
    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id(userDto.getId());
        user.name(userDto.getName());
        user.email(userDto.getEmail());

        return user.build();
    }
}
