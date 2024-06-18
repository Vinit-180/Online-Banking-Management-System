package com.example.Online.Banking.Management.System.services;

import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.User;
import com.example.Online.Banking.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

//    save user
    public ApiManager<User> saveUser(User u){
        try {
            repository.save(u);
            return new ApiManager<>(u, HttpStatus.OK, "User Created Successfully");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }
//list of all user
    public ApiManager<List<User>> findAllUsers(){
        try{
            return new ApiManager<>(repository.findAll(),HttpStatus.OK,"Retrieved All Users Data");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

//    find user by id
    public ApiManager<User> findUserbyId(@PathVariable(value="id") Long id){
        try{
            System.out.print("id"+id);
            Optional<User>user=repository.findById(id);
            if(user.isPresent()){
                return new ApiManager<>(user.get(), HttpStatus.OK, "User fetch Successfully");
            }
            else{
                return new ApiManager<>(HttpStatus.NOT_FOUND,"Data Found","User  not Found with id:"+id);
            }
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

//    update user data
    public ApiManager<User> updateUserById(long id,User u){
        try{

            Optional<User>user = repository.findById(id);
            if(user.isPresent()) {
//                optionalUser.get().setddress(u.getAddress())
                user.get().setName(u.getName());
                User u2 = repository.save(user.get());
                return new ApiManager<>(u2,HttpStatus.OK,"User Data updated Successfully");
            }
            return new ApiManager<>(HttpStatus.NOT_FOUND,"Data Not Found","No User Found ");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

    public ApiManager<User> userSignIn(String username,String password ){
        try{
            System.out.println(username+"  "+password);
               User u=repository.findByUsername(username);
               System.out.println(u.getPassword()+"  "+u.getPassword().equals(password));
               if(u!=null && u.getPassword().equals(password)){
                   return new ApiManager<>(u, HttpStatus.OK, "Login Successful");
               }
               else{
                   return new ApiManager<>(HttpStatus.UNAUTHORIZED, "Invalid Credentials", "Unauthorized");
               }


        }catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

//    public User findByUsername(String username) {
//        return rpository.findByUsername(username);
//    }
}
