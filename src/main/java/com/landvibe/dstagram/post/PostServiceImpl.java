package com.landvibe.dstagram.post;

import com.landvibe.dstagram.post.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    public PostServiceImpl() {
    }

    @Override
    public List<Post> getPosts() {
        return null;
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(int id, Post post) {
        return null;
    }

    @Override
    public void deletePost(int id) {

    }
}
