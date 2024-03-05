package org.example.guest_app.services;

import org.example.guest_app.entities.Comment;
import org.example.guest_app.requests.CreateCommentRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId);

    Comment saveComment(CreateCommentRequest commentRequest);

    Comment updateComment(Optional<Long> postId, Optional<Long> userId,CreateCommentRequest request);

    Comment getCommentById(Long id);
    Comment updateCommentById(Long id,CreateCommentRequest request);
    void deleteComments(Optional<Long> postId, Optional<Long> userId);



    void deleteCommentById(Long id);


}
