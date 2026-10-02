package org.example.service;

import org.example.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private Map<Integer, User> userDB;

     public UserService(){
         userDB = new HashMap<>();
     }

     public User createUser(User userReq){
         userDB.put(userReq.getId() , userReq);
         return  userReq;
     }
     public List<User> getAllUser(){
         return (List<User>) userDB.values();
     }
     public User getUserById(Integer id){
         return userDB.getOrDefault(id , null);
     }
}
