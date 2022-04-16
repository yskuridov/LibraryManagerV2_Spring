package com.skuridov.tp3.tp3spring.repository;

import com.skuridov.tp3.tp3spring.model.Fine.Fine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FineRepository extends JpaRepository<Fine, Long> {
    List<Fine> findAllByMemberId(Long id);
}
