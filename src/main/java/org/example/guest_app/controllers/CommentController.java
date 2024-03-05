package org.example.guest_app.controllers;

import org.example.guest_app.entities.Comment;
import org.example.guest_app.requests.CreateCommentRequest;
import org.example.guest_app.services.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentServiceImpl commentService;
    public CommentController(CommentServiceImpl commentService){
        this.commentService = commentService;
    }



    /* Burası aşağıdaki 4 endpoint'e de aynı anda hizmet verecektir.

        ".../comments" endpoint'i için HTTP GET metodu
        ".../comments?postId={postId}" endpoint'i için HTTP GET metodu
        ".../comments?userId={userId}" endpoint'i için HTTP GET metodu
        ".../comments?postId={postId}&userId={userId}" endpoint'i için HTTP GET metodu.
    */
    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId , @RequestParam Optional<Long> userId){
        return commentService.getAllComments(postId,userId);
    }

    /*YENİ BİR COMMENT OLUŞTURMA METODU*/
    /*
        ".../comments" endpoint'i için HTTP POST metodu
    */
    @PostMapping
    public Comment save(@RequestBody CreateCommentRequest commentRequest){
        return commentService.saveComment(commentRequest);
    }

    /* POSTID VE USERID 'Sİ VERİLEN COMMENTİ GÜNCELLEME METODU
     */
    //".../comments?postId={postId}&userId={userId}" endpoint'i için HTTP GET metodu
    /*Burada güncelleme yapabilmek için aynı anda hem postId hem de userId'e sahip olmalıyız
     */
    @PutMapping
    public Comment updateComment(@RequestParam Optional<Long> postId , @RequestParam Optional<Long> userId,@RequestBody CreateCommentRequest request){
        return commentService.updateComment(postId,userId,request);
    }


    /*BİR POSTTAKİ BÜTÜN YORUMLARI SİL YA DA BİR USER'IN BÜTÜN YORUMLARINI SİL YA DA
    * BİR POSTTAKİ ÖZEL BİR USER'A AİT OLAN TÜM COMMENTLARI SİL METODU*/
    @DeleteMapping
    public void deleteComments(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId){
        commentService.deleteComments(postId,userId);
    }

    /*COMMENTID'Sİ VERİLEN BİR COMMENT'İ GETİR*/
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable Long id){
        return commentService.getCommentById(id);
    }

    /*COMMENTID'Sİ VERİLEN BİR COMMENT'İ SİL*/
    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable Long id){
        commentService.deleteCommentById(id);
    }

    /*COMMENTID'Sİ VERİLEN BİR COMMENT'İ GÜNCELLE*/
    @PutMapping("/{id}")
    public Comment updateCommentById(@PathVariable Long id,@RequestBody CreateCommentRequest request){
        return commentService.updateCommentById(id,request);
    }










}
