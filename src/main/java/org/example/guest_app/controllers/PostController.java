package org.example.guest_app.controllers;

import jakarta.transaction.Transactional;
import org.example.guest_app.entities.Post;
import org.example.guest_app.requests.PostCreateRequest;
import org.example.guest_app.services.impl.PostServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostServiceImpl postService ;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }


    // ".../posts" endpoint'i için HTTP POST metodu
    @PostMapping
    public Post save(@RequestBody PostCreateRequest post){
        return postService.save(post);
    }


    //requestParam sayesinde hem "/posts" hem de "/posts/?userId={userId}" endpointleri için
    //cavap alacağımız bir şekil ortaya çıkmıştır.Yani  userId verilmezse eğer diğer bütün postları
    //getirebileceğiz.

    // ".../posts?userId={userId}" endpoint'i için HTTP GET metodu
    /* ".../posts" endpoint'i için HTTP GET metodu*/
    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    /*".../posts?userId={userId}&id={postId}" endpoint'i için HTTP PUT metodu*/
    @PutMapping
    public Post updatePostByUserIdAndPostId(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId,@RequestBody PostCreateRequest postCreateRequest){
        return postService.updatePostByUserIdAndPostId(userId,postId,postCreateRequest);
    }

    /*".../posts?userId={userId}&id={postId}" endpoint'i için HTTP DELETE metodu*/
    @DeleteMapping
    @Transactional
    public void deletePostByUserIdAndPostId(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
        postService.deletePostByUserIdAndPostId(userId,postId);
    }

    /*".../posts/{postId}" endpoint'i için HTTP GET metodu*/
    @GetMapping ("/{postId}")
    public Post getPost(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    /*".../posts/{postId}" endpoint'i için HTTP PUT metodu*/
    @PutMapping("/{postId}")
    public Post update(@RequestBody PostCreateRequest post , @PathVariable Long postId){
        return postService.updatePostByPostId(post,postId);
    }

    /* ".../posts/{postId}" endpoint'i için HTTTP DELETE metodu*/
    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Long postId){
        postService.deletePost(postId);
    }

}
