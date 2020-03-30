package com.example.springsinginexample.productodefinale.repository;


import com.example.springsinginexample.productodefinale.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
