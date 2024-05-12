package com.springboot.mew_bank.bankuser;

import com.springboot.mew_bank.appuser.AppUser;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class BankUser {
    @Id
    @SequenceGenerator(
            name = "bank_user_generator"
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long moneyAmount;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;
}
