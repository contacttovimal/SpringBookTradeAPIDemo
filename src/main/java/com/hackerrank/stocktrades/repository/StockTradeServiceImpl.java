package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StockTradeServiceImpl implements StockTradeServiceInterface {
    private static AtomicInteger tradeNumber = new AtomicInteger(0);

    @Autowired
    StockTradeRepository stockTradeRepository;

    @Override
    public StockTrade addNewTrade(StockTrade newTrade) {
        if(newTrade.getId()!=null){
            throw new RuntimeException("Trade Id is not blank");
        }
        newTrade.setId(tradeNumber.incrementAndGet());
        stockTradeRepository.save(newTrade);
        return newTrade;
    }

    @Override
    public Collection<StockTrade> getAllTrade() {
        return stockTradeRepository.findAll();
    }

    @Override
    public StockTrade findTradeById(Integer tradeId) {
        Optional<StockTrade> trade = stockTradeRepository.findById(tradeId);
        return trade.isPresent()?trade.get():null;
    }

    @Override
    public boolean isExists(Integer tradeId) {
        return stockTradeRepository.existsById(tradeId);
    }
}

