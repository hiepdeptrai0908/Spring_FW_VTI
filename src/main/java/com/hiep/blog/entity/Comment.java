package com.hiep.blog.entity;

import com.hiep.blog.generator.CommentIdGenerator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @Column(name = "id", length = 50)
    @GenericGenerator(
            name = "generator-comment-id-custom",
            type = CommentIdGenerator.class
    )
    @GeneratedValue(generator = "generator-comment-id-custom")
    private String id;

    @Column(name = "name", length = 75, nullable = false)
    private String name;

    @Column(name ="email", length = 100, nullable = false)
    private String email;

    @Column(name = "body", length = 100, nullable = false)
    private String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;
}
