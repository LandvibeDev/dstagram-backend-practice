package com.landvibe.dstagram.post.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class Post {

    @Column(updatable = false, nullable = false)
    private int id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    public Post() {
    }

    public Post(int id, String title, String contents, String imageUrl, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.created = created;
        this.updated = updated;
    }
}
