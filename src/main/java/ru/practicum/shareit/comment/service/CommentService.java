package ru.practicum.shareit.comment.service;

import ru.practicum.shareit.comment.dto.CommentDto;

import java.util.List;
public interface CommentService {
    CommentDto comment(Long userId, Long itemId, CommentDto commentDto);
    List<CommentDto> commentDtos(Long itemId);

}
