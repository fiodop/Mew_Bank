package com.springboot.mew_bank.bankuser;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/bank")
public class BankController {
    private final BankUserService bankUserService;

    @GetMapping(path = "/money_amount/{userId}")
    public ResponseEntity<Double> checkMoney(@PathVariable Long userId) {
        Double balance = Double.valueOf(bankUserService.checkBalance(userId));
        return ResponseEntity.ok(balance);
    }

    @PostMapping(path = "/send_money")
    public String sendMoney(@RequestBody BankUser userFrom, @RequestBody BankUser userTo, long amount){
        return bankUserService.sendMoney(userFrom.getId(), userTo.getId(), amount);
    }
}
