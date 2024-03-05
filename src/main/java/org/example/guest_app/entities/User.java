package org.example.guest_app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    private String userName;
    private String password;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonBackReference(value = "post-user")
    private List<Post> postList ;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "comment-user")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "likeTable-user")
    private List<LikeTable> likeList;
}
