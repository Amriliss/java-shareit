package ru.practicum.shareit.requests.pagerequestmanager;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.exception.BadRequestException;

public class PageRequestManager {

    public static PageRequest form(Integer from, Integer size, Sort.Direction direction, String properties) {
        if (from < 0) throw new BadRequestException("From paging must be equal 0 or greater");
        if (size <= 0) throw new BadRequestException("Page size must be greater than 0");
        Sort sort = Sort.by(direction, properties);
        return PageRequest.of(from / size, size, sort);
    }

}
