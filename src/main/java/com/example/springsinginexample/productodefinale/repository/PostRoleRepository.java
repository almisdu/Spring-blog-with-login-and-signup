package com.example.springsinginexample.productodefinale.repository;

import com.example.springsinginexample.productodefinale.model.PostEmails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRoleRepository extends JpaRepository<PostEmails, Integer> {

    PostEmails findByPostid(Integer postid);

}
