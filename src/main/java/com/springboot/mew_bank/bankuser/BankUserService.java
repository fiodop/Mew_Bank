package com.springboot.mew_bank.bankuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BankUserService{
    @Autowired
    private BankUserRepository bankUserRepository;

    public String checkBalance(Long id) {
         Optional<BankUser> user = bankUserRepository.findById(id);
        if (!user.isPresent()){
            throw new IllegalStateException("There is no user with this id");
        }

        BankUser bankUser = user.get();
        String balance = bankUser.getMoneyAmount().toString();
        return balance;
    }


    public String sendMoney(long IdUserFrom, Long IdUserTo, long amount) {
        Optional<BankUser> userFrom = bankUserRepository.findById(IdUserFrom);
        Optional<BankUser> userTo = bankUserRepository.findById(IdUserTo);

        if(!userFrom.isPresent() || !userTo.isPresent()){
            throw new IllegalStateException("There is no user with this id");
        }

        BankUser bankUserFrom = userFrom.get();
        BankUser bankUserTo = userTo.get();

        if (bankUserFrom.getMoneyAmount() < amount){
            throw new IllegalStateException("insufficient funds");
        }

        bankUserFrom.setMoneyAmount(bankUserFrom.getMoneyAmount() - amount);
        bankUserTo.setMoneyAmount(bankUserTo.getMoneyAmount() + amount);

        bankUserRepository.save(bankUserFrom);
        bankUserRepository.save(bankUserTo);

        return "money sended";
    }
}
