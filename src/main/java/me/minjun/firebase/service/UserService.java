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

    public void addRegistrationTokenToUser(String email, String registrationToken){
        Optional<User> optionalUser = userRepo.findById(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setRegistrationToken(registrationToken);
            userRepo.save(user);
        }
    }

    public void addCustomTokenToUser(String email, String customToken){
        Optional<User> optionalUser = userRepo.findById(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setCustomToken(customToken);
            userRepo.save(user);
        }
    }

    public void addIdTokenToUser(String email, String idToken){
        Optional<User> optionalUser = userRepo.findById(email);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setIdToken(idToken);
            userRepo.save(user);
        }
    }

    public String getTokenByEmail(String email) {
        Optional<User> optionalUser = userRepo.findById(email);
        return optionalUser.map(User::getIdToken).orElse(null);
    }
}
