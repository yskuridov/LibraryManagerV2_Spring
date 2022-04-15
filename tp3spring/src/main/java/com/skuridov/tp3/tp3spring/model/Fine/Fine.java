package com.skuridov.tp3.tp3spring.model.Fine;


import com.skuridov.tp3.tp3spring.model.User.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

public class Fine {
    @Id
    @GeneratedValue
    private long id;
    private final double FINE_AMOUNT = 0.25;
    private int nbOfDays;
    @ManyToOne
    @JoinColumn(name = "MEMBER_id")
    private Member member;

    public Fine(int nbOfDays, Member member){
        this.nbOfDays = nbOfDays;
        this.member = member;
    }

}
