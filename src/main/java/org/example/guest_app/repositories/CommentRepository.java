package org.example.guest_app.repositories;

import org.example.guest_app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPostIdAndUserId(Long postId,Long userId);
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByUserId(Long userId);
    List<Comment> findAll();

    Comment findCommentByUserIdAndPostId(Long userId,Long postId);

    void deleteAllByUserIdAndPostId(Long userId,Long postId);
    void deleteAllByPostId(Long postId);
    void deleteAllByUserId(Long userId);
}
