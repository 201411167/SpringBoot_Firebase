package me.minjun.firebase.service;

import me.minjun.firebase.domain.user.User;
import me.minjun.firebase.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public void insertUser(String email, String name){
        userRepo.insert(User.builder().email(email).name(name).build());
    }

    public List<User> listUser(){
        return userRepo.findAll();
    }

    public void deleteUser(String email){
        Optional<User> optionalUser = userRepo.findById(email);
        optionalUser.ifPresent(user -> userRepo.delete(user));
    }

    public User findUser(String email){
        Optional<User> optionalUser = userRepo.findById(email);
        return optionalUser.orElse(null);
    }
}
