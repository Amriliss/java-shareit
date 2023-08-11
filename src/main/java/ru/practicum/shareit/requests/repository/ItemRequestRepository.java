package ru.practicum.shareit.requests.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.requests.model.ItemRequest;

import java.util.List;
import java.util.Optional;
@Repository
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long> {
    List<ItemRequest> findAllByRequesterIdOrderByCreatedDesc(Long userId);

    Optional<ItemRequest> findItemRequestById(Long id);

    List<ItemRequest> findAllByRequesterIdIsNot(Long userId, PageRequest pageRequest);
}