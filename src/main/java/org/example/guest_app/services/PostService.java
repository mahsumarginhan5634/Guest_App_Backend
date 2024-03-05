package org.example.guest_app.services;

import org.example.guest_app.entities.Post;
import org.example.guest_app.requests.PostCreateRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts(Optional<Long> userId);
    Post getPostById(Long postId);

    Post save(PostCreateRequest post);

    Post updatePostByPostId(PostCreateRequest post, Long postId);

    void deletePost(Long postId);

    Post updatePostByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId,PostCreateRequest postCreateRequest);

    void deletePostByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId);


    Post findPostById(Long postId);
}
