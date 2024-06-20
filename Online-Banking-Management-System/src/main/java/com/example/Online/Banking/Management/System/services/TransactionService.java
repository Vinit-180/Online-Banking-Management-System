package com.example.Online.Banking.Management.System.services;

import com.example.Online.Banking.Management.System.ApiManager;
import com.example.Online.Banking.Management.System.models.Account;
import com.example.Online.Banking.Management.System.models.Transaction;
import com.example.Online.Banking.Management.System.repositories.AccountRepository;
import com.example.Online.Banking.Management.System.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository rep;

    @Autowired
    AccountRepository accountRepository;

//    save transaction
    public ApiManager<Transaction> TransferMoney(Transaction t){
        try{
            Long SourceAccountId=t.getSourceAccountId();
            Long TargetAccountId=t.getTargetAccountId();
            Optional<Account> source=accountRepository.findById(SourceAccountId);
            Optional<Account> target=accountRepository.findById(TargetAccountId);
//            both are present or not
            if(source.isPresent() && target.isPresent()) {
//              balance is sufficient or not for transaction.
              if(isAmountAvailable(source.get(),t.getAmount()))
                {
//                    if both have same account id means withdrawal
                    if (SourceAccountId.equals(TargetAccountId)) {
                        return withdrawTransaction(t);
                    }
//                    else transfer money
                    else {
                        if(isAmountAvailable(source.get(), t.getAmount())){
                            boolean trans1 =updateAccountBalance(source.get(),t.getAmount(),"withdraw");
                            if(trans1) {
                                boolean trans2 = updateAccountBalance(target.get(), t.getAmount(), "deposit");
                                if (trans2) {
                                    source.get().addTransaction(t);
                                    target.get().addTransaction(t);
                                    t.setTimestamp(LocalDateTime.now());
                                    rep.save(t);
                                    return new ApiManager<>(t,HttpStatus.OK,"Transfer Money Successfull from "+source.get().getId()+" to "+target.get().getId());
                                } else {
                                    updateAccountBalance(source.get(),t.getAmount(),"deposit");
                                    return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,"Transaction Failed","If your money is debited it will be return within few seconds");
                                }
                            }
                        }
                    }
                }
                else{
                    return new ApiManager<>(HttpStatus.BAD_REQUEST,"Insufficient Balance","Account "+source.get().getId()+" Doesn't have sufficient Balance");
                }
            }

            return new ApiManager<>(t,HttpStatus.OK,"Transaction Created Successfully");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

// withdraw
public ApiManager<Transaction>withdrawTransaction(Transaction t){
        try{
            Long SourceAccountId=t.getSourceAccountId();
            Optional<Account> source=accountRepository.findById(SourceAccountId);
            if(source.isPresent()) {
                if (updateAccountBalance(source.get(), t.getAmount(), "withdraw")) {
                    t.setTimestamp(LocalDateTime.now());
                    Account account = source.get();
                    System.out.println(account.getTransactions());
//                    List<Transaction>li= account.getTransactions();
//                    if(li.isEmpty()){
//                        li=new ArrayList<Transaction>();
//                    }
//                    li.add(t);
                    t.setAccount(account); // Associate transaction with account
                    rep.save(t);


                    System.out.println(t);
                    List<Transaction>l2=account.getTransactions();
                    l2.add(t);
//                    account.getTransactions().add(t2.get());
//                    System.out.println(t2);
//                    System.out.println("gggggggggggggggg"+account.getTransactions());
                    account.setTransactions(l2);
                    accountRepository.save(account);

//                    System.out.println("---------------"+accountRepository.save(account));
                    System.out.println(account.getTransactions());
//                    accountRepository.flush();
                    return new ApiManager<>(HttpStatus.OK, "Transaction Success", "Amount Withdraw Successfully");
                } else {

                    return new ApiManager<>(HttpStatus.BAD_REQUEST, "Transaction Failed", "Transaction Failed");
                }
            }
            else{
                return new ApiManager<>(HttpStatus.NOT_FOUND,"Transaction Failed","Account not found with id:"+t.getSourceAccountId());
            }
        }
        catch (Exception e){
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

    public boolean isAmountAvailable(Account source,double amount){
        return (source.getBalance()-amount)>=0;
    }

    public boolean updateAccountBalance(Account src,double amount,String status){
        if(status.equals("withdraw")) {
            double remaining = src.getBalance() - amount;
            try {
                src.setBalance(remaining);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        else{
            double newbalance = src.getBalance() +amount;
            try {
                src.setBalance(newbalance);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }

//    get list of all transaction
    public ApiManager<List<Transaction>> listOfAllTrans(){
        try{
            return new ApiManager<>(rep.findAll(),HttpStatus.OK,"Retrieve all the Transactions successfully");
        }
        catch (Exception e)
        {
            return new ApiManager<>(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),"Internal Server Error");
        }
    }

}
