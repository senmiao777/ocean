package com.frank.repository.mysql;

import com.frank.entity.mysql.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {

    @Query(value = "select * from stock_base_info where code= ?1", nativeQuery = true)
    Stock findByStockCode(String stockCode);
}
