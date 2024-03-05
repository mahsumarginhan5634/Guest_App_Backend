package org.example.guest_app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonManagedReference(value = "likeTable-user")
    private User user;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postId",referencedColumnName = "id")
    @JsonManagedReference(value = "liketable-post")
    private Post post;
}
