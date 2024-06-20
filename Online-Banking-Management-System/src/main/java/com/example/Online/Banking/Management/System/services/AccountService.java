package com.example.Online.Banking.Management.System.services;

import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.Account;
import com.example.Online.Banking.Management.System.models.User;
import com.example.Online.Banking.Management.System.repositories.AccountRepository;
import com.example.Online.Banking.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository rep;

    @Autowired
    UserService userService;
//    create account
    public ApiManager<Account> saveAccount(Account acc,Long userId){
        try{
            ApiManager<User> user;
                user = userService.findUserbyId(userId);
            if(user.getStatusCode()!=200){
                return new ApiManager<>(HttpStatus.NOT_FOUND,user.getMessage(),"User not found with id"+userId);
            }
            rep.save(acc);
            Optional<Account> account=rep.findById(acc.getId());
            return new ApiManager<>(account.get(), HttpStatus.OK,"Account Created Successfully");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

//    list all accounts
    public ApiManager<List<Account>> findAllAccounts(){
        try{
            return new ApiManager<>(rep.findAll(),HttpStatus.OK,"Retrieved All Accounts Data");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }
//    find account by account id
    public ApiManager<Map<String, Object>> findAccountById(@PathVariable(value = "id") Long id){
        try{
            Optional<Account> acc=rep.findById(id);
            if(acc.isPresent()){
                User user = userService.findUserbyId(acc.get().getUserId()).getData();
                Map<String, Object> response = new HashMap<>();
                response.put("account", acc.get());
                response.put("user", user);
                return new ApiManager<>(response,HttpStatus.OK,"Fetch Account successfully");
            }
            else{
                return new ApiManager<>(HttpStatus.NOT_FOUND,"Data Not Found","No Account Found with id:"+id);
            }
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }


//    get Balance
    public ApiManager<Double> getBalanceByAccountId(@PathVariable(value = "id")Long id){
        try{
            Optional<Account> acc=rep.findById(id);
            if(acc.isPresent()){
                return new ApiManager<>(acc.get().getBalance(),HttpStatus.OK,"Balance Retrieved Successfully");
            }
            else{
                return new ApiManager<>(HttpStatus.NOT_FOUND,"Data Not Found","No Account Found with id:"+id);
            }
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

//    find account details by user id


//    delete account by account id
    public ApiManager<String> deleteAccountById(@PathVariable(value = "id") Long id){
        try{
            Optional<Account>acc=rep.findById(id);
            if(acc.isPresent()){
                rep.deleteById(id);
                return new ApiManager<>("Success",HttpStatus.OK,"Account Deleted Successfully");
            }
            else {
                return new ApiManager<>(HttpStatus.NOT_FOUND,"Data Not Found","No Account Exist with id: "+id);
            }
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }
}
