package com.example.bookshelf.comment.internal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findFirst5ByBook_IdOrderByCreationTime(UUID bookId);

    Page<Comment> findAllByBook_Id(UUID bookId, Pageable pageable);

    void deleteByBook_Id(UUID bookId);
}
