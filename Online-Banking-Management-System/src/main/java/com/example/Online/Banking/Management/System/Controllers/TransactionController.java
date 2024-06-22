package com.example.Online.Banking.Management.System.Controllers;

import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.Transaction;
import com.example.Online.Banking.Management.System.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    public TransactionService transService;

    @PostMapping("/withdraw")
    public ApiManager<Transaction> withdrawAmount(@RequestBody Transaction t){
        return transService.withdrawTransaction(t);
    }

    @PostMapping("/transfer")
    public ApiManager<Transaction> transferAmount(@RequestBody Transaction t){
        System.out.println(t.getTargetAccountId());
        return transService.TransferMoney(t);
    }
    @PostMapping("/deposit")
    public ApiManager<Transaction> depositAmount(@RequestBody Transaction t){
        System.out.println(t.getTargetAccountId());
        return transService.depositransaction(t);
    }

    @GetMapping("/getalltransactions")
    public ApiManager<List<Transaction>> alltransaction(){
        return transService.listOfAllTrans();
    }
}
