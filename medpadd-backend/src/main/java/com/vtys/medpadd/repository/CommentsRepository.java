package com.vtys.medpadd.repository;

import com.vtys.medpadd.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, UUID> {
    List<Comments> findByContentId(UUID contentId);

    List<Comments> findByUserId(UUID userId);
}
