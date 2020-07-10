package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    private Map<Integer, Post> cachedPosts = new HashMap<>();

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPosts() {
        return new ArrayList<>(cachedPosts.values());
    }

    @Override
    public Post createPost(Post post) {
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

    @Override
    public Post updatePost(int id, Post post) {
        LocalDateTime updatedTime = LocalDateTime.now();
        post.setUpdated(updatedTime);
        post.setId(id);

        if (cachedPosts.containsKey(post.getId())) {
            return cachedPosts.put(id, post);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }

    @Override
    public void deletePost(int id) {
        if (cachedPosts.containsKey(id)) {
            cachedPosts.remove(id);
        } else {
            throw new RuntimeException("Not found post: " + id);
        }
    }
}
