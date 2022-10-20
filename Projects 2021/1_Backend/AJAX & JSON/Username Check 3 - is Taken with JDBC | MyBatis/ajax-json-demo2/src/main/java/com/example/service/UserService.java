package com.example.service;

import com.example.bean.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<User> findByName(String name) throws IOException;

}
