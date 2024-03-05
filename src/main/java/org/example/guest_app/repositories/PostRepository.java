package org.example.guest_app.repositories;

import org.example.guest_app.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    void deletePostById(Long id);
    List<Post>  findAllByUserId(Long userId);
    Post findPostById(Long id);
}
