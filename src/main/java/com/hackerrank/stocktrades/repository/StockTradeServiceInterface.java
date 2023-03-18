package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface StockTradeServiceInterface {
    public StockTrade addNewTrade(StockTrade newTrade);
    public Collection<StockTrade> getAllTrade();
    public StockTrade findTradeById(Integer tradeId);
    public boolean isExists(Integer tradeId);
}
