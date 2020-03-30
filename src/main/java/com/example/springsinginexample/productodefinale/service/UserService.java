package com.example.springsinginexample.productodefinale.service;

import com.example.springsinginexample.productodefinale.model.User;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}
