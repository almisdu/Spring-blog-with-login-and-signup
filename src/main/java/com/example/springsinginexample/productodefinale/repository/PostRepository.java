package com.example.springsinginexample.productodefinale.repository;

import com.example.springsinginexample.productodefinale.model.Post;
import com.example.springsinginexample.productodefinale.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByPostowner(String postowner);

}
