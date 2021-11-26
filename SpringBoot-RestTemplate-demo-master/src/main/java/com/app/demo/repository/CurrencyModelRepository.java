package com.app.demo.repository;


import com.app.demo.entity.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyModelRepository extends JpaRepository<CurrencyModel, Long> {
    public List<CurrencyModel> findByCur(String cur);
}
