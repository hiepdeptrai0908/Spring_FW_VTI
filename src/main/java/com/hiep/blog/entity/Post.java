package com.hiep.blog.entity;

import com.hiep.blog.converter.PostStatusConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private String updatedAt;

    @OneToMany(mappedBy = "post")
    private List<Comment> comment;

    public enum Status {
        OPENING, CLOSED
    }
}
