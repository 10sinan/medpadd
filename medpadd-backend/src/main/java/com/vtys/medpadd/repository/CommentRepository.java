package com.vtys.medpadd.repository;

import com.vtys.medpadd.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
