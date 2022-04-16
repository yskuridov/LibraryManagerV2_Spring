package com.skuridov.tp3.tp3spring.repository;

import com.skuridov.tp3.tp3spring.model.User.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberById(long id);
}
