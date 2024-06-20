package com.example.Online.Banking.Management.System.Controllers;

import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.Account;
import com.example.Online.Banking.Management.System.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    public AccountService accService;

    @PostMapping("/addaccount")
    public ApiManager<Account> addAccount(@RequestBody Account acc){
        return accService.saveAccount(acc,acc.getUserId());
    }

    @GetMapping("/getallaccount")
    public ApiManager<List<Account>> listAllAccounts(){
        return accService.findAllAccounts();
    }

    @GetMapping("/getaccount/{id}")
    public ApiManager<Map<String, Object>> getAccountById(@PathVariable(value = "id") long id){
        return accService.findAccountById(id);
    }

    @DeleteMapping("/deleteaccount/{id}")
    public ApiManager<String> deleteAccountById(@PathVariable(value = "id") Long id){
        return accService.deleteAccountById(id);
    }

    @GetMapping("/getbalance/{id}")
    public ApiManager<Double> getBalanceById(@PathVariable(value = "id") Long id){
        return accService.getBalanceByAccountId(id);
    }
}
