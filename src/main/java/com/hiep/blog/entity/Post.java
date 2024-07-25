package com.hiep.blog.entity;

import com.hiep.blog.converter.PostStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "content", length = 100, nullable = false)
    private String content;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "status")
    // @Enumerated(value = EnumType.STRING)
    @Convert(converter = PostStatusConverter.class)
    private Status status;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    public enum Status {
        OPENING, CLOSED
    }
}
