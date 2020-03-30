package com.example.springsinginexample.productodefinale.service;

import com.example.springsinginexample.productodefinale.model.Post;
import com.example.springsinginexample.productodefinale.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> listAll(){
        return repo.findAll();
    }

    public void save(Post post){
        repo.save(post);
    }

    public Post get(Long id) {
        return repo.findById(id).get();
    }

    public void delete (Long id) {
        repo.deleteById(id);
    }

}
