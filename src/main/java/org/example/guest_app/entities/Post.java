package org.example.guest_app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonManagedReference(value = "post-user")
    private User user;


    @OneToMany(mappedBy = "post")
    @JsonBackReference(value = "likeTable-post")
    private List<LikeTable> likeList;

    @OneToMany(mappedBy = "post")
    @JsonBackReference(value = "comment-post")
    private List<Comment> commentList;

}
