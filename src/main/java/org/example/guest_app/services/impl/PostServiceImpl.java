package org.example.guest_app.services.impl;

import org.example.guest_app.entities.Post;
import org.example.guest_app.entities.User;
import org.example.guest_app.repositories.PostRepository;
import org.example.guest_app.requests.PostCreateRequest;
import org.example.guest_app.services.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserServiceImpl userService;
    public PostServiceImpl(PostRepository postRepository,UserServiceImpl userService){
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public Post save(PostCreateRequest postRequest) {
        User user = userService.findUserById(postRequest.getUserId());
        if(user != null){
            Post post = toEntity(postRequest);
            post.setUser(user);
            return postRepository.save(post);
        }
        else {
            //custom exception fırlat.
            return null;
        }
    }

    @Override
    public Post updatePostByPostId(PostCreateRequest post, Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post existPost = optionalPost.get();
            existPost.setText(post.getText());
            existPost.setTitle(post.getTitle());
            return postRepository.save(existPost);
        }
        else{
            //post olmadığına dair custom exception fırlatılacak.
            return null;
        }

    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post updatePostByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId,PostCreateRequest postCreateRequest) {
        User user = null;
        Post post = null;
        if(userId.isPresent()){
            user = userService.findUserById(userId.get());
            if(user == null){
                //userın bulunamadığına dair exception atılacak
                return null;
            }
            if(postId.isPresent()){
                post = postRepository.findById(postId.get()).orElse(null);
                if(post == null){
                    //postun bulunamadığına dair exception atılacak
                    return null;
                }
                else{
                    post.setText(postCreateRequest.getText());
                    post.setTitle(postCreateRequest.getTitle());
                    return postRepository.save(post);
                }
            }
            else{
                //path'de postId verilmemiş demektir.
                return null;
            }
        }

        //path değişkenlerinde userId bulunmayınca burası çalışacaktır.
        return null;
    }

    @Override
    public void deletePostByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId) {
        User user = null;
        Post post = null;
        if(userId.isPresent()){
            user = userService.findUserById(userId.get());
            if(user == null){
                //verilen idli bir user yok , akışı sonlandır.
                System.out.println("Verilen id'li bir user yok");
                return;
            }
            if(postId.isPresent()){
                post = postRepository.findById(postId.get()).orElse(null);
                if(post == null){
                    //verilen idli bir post yok , akışı sonlandır.
                    System.out.println("Verilen id'li bir post yok");
                    return;
                }
                else{
                    postRepository.deletePostById(postId.get());
                    System.out.println(userId.get() +" idli user'ın "+postId.get()+" idli post'u başarıyla silindi");
                }
            }
            else{
                //path'de postId boş gelmiş
                System.out.println("Path'de postId bulunmuyor.");
           }
        }
        else{
            //path'de userId boş gelmiş
            System.out.println("Path'de userId bulunmuyor.");
        }
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId);
    }


    private List<Post> getAllPostsWithUserId(Long userId){
        return postRepository.findAllByUserId(userId);
    }
    private List<Post> findAllPosts(){
        return postRepository.findAll();
    }
    @Override
    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()){
            return getAllPostsWithUserId(userId.get());
        }
        else{
            return findAllPosts();
        }
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    private Post toEntity(PostCreateRequest postCreateRequest) {
        Post post = new Post();
        post.setTitle(postCreateRequest.getTitle());
        post.setText(postCreateRequest.getText());
        return post;
    }
}
