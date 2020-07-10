package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {

    private Map<Integer, Post> cachedPosts = new HashMap<>();

    public PostController() {
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts() {
        return new ArrayList<>(cachedPosts.values());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@RequestBody Post post) {
        LocalDateTime createdTime = LocalDateTime.now();
        post.setCreated(createdTime);
        post.setUpdated(createdTime);

        if (cachedPosts.containsKey(post.getId())) {
            throw new RuntimeException("This post already exists: " + post.getId());
        } else {
            cachedPosts.put(post.getId(), post);
            return post;
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Post updatePost(@PathVariable int id, @RequestBody Post post) {
        LocalDateTime updatedTime = LocalDateTime.now();
        post.setUpdated(updatedTime);
        post.setId(id);

        if (cachedPosts.containsKey(post.getId())) {
            return cachedPosts.put(id, post);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) {
        if (cachedPosts.containsKey(id)) {
            cachedPosts.remove(id);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
