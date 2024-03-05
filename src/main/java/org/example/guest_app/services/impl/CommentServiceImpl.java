package org.example.guest_app.services.impl;

import org.example.guest_app.entities.Comment;
import org.example.guest_app.entities.Post;
import org.example.guest_app.entities.User;
import org.example.guest_app.repositories.CommentRepository;
import org.example.guest_app.requests.CreateCommentRequest;
import org.example.guest_app.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;

    private final PostServiceImpl postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserServiceImpl userService, PostServiceImpl postService){
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public Comment saveComment(CreateCommentRequest commentRequest) {
        User user = userService.findUserById(commentRequest.getUserId());
        Post post = postService.findPostById(commentRequest.getPostId());

        Comment comment = toEntity(commentRequest);
        if(user != null && post != null){
            comment.setUser(user);
            comment.setPost(post);
            return commentRepository.save(comment);
        }


        return null;
    }

    @Override
    public Comment updateComment(Optional<Long> postId, Optional<Long> userId,CreateCommentRequest request) {

        if(postId.isPresent() && userId.isPresent()){
            Comment comment = commentRepository.findCommentByUserIdAndPostId(userId.get(),postId.get());
            comment.setText(request.getText());
            commentRepository.save(comment);
            return comment;
        }
        else {
            //custom exception fırlat.
            return null;
        }
    }

    @Override
    public void deleteComments(Optional<Long> postId, Optional<Long> userId) {
        //Her iki parametre de varsa belli bir user'ın belli bir postta yapmış olduğu
        //tüm commentleri sil.
        if(userId.isPresent() && postId.isPresent()){
            commentRepository.deleteAllByUserIdAndPostId(userId.get(),postId.get());
        }
        //Bir user'a ait olan tüm commentleri sil.
        else if(userId.isPresent()){
            commentRepository.deleteAllByUserId(userId.get());
        }
        //Bir post'daki tüm yorumları sil.
        else if(postId.isPresent()){
            commentRepository.deleteAllByPostId(postId.get());
        }
        else{
            //custom exception fırlat = postId ve userId her ikisi de boş gelmemeli.

        }
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            return comment.get();
        }
        else{
            // comment bulunamadı custom exception fırlat.
            return null;
        }
    }

    @Override
    public Comment updateCommentById(Long id,CreateCommentRequest request) {
        Optional<Comment> comment = commentRepository.findById(id);

        if(comment.isPresent()){
            Comment updatedComment = new Comment();
            updatedComment.setId(comment.get().getId());
            updatedComment.setPost(comment.get().getPost());
            updatedComment.setUser(comment.get().getUser());
            updatedComment.setText(request.getText());
            return commentRepository.save(updatedComment);
        }
        else{
            //comment bulunamadı custom exception'ı fırlat;
            return null;
        }

    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    private Comment toEntity(CreateCommentRequest commentRequest){
        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        return comment;
    }

    @Override
    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {

        if(postId.isPresent() && userId.isPresent()){
            return getAllCommentsByPostIdAndUserId(postId.get(),userId.get());
        }
        else if(postId.isPresent()){
            return getAllCommentsByPostId(postId.get());
        }
        else if(userId.isPresent()){
            return getAllCommentsByUserId(userId.get());
        }
        else{
            //her ikisi de yok.Direkt bütün commentleri getir.
            return getAllCommentsWithoutPostIdAndUserId();
        }

    }





    //Yardımcı metodlar
    private List<Comment> getAllCommentsByPostIdAndUserId(Long postId, Long userId) {
        return commentRepository.findAllByPostIdAndUserId(postId,userId);
    }
    private List<Comment> getAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
    private List<Comment> getAllCommentsByUserId(Long userId) {
        return commentRepository.findAllByUserId(userId);
    }
    private List<Comment> getAllCommentsWithoutPostIdAndUserId() {
        return commentRepository.findAll();
    }


}
