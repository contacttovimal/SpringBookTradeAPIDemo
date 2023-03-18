package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.repository.StockTradeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController

public class StockTradeRestController {

    @Autowired
    StockTradeServiceInterface stockTradeImpl;

    @PostMapping(path = "/trades")
    public ResponseEntity<StockTrade> postTrade(@Valid @RequestBody StockTrade newTrade){
        StockTrade result_trade = stockTradeImpl.addNewTrade(newTrade);
        return new ResponseEntity<>(result_trade,HttpStatus.CREATED);
    }
    @GetMapping(path = "/trades" )
    public ResponseEntity<Collection<StockTrade>> getAllTrades(){
        Collection<StockTrade> trades = stockTradeImpl.getAllTrade();
        System.out.println("trades size: " + trades.size());
        return new ResponseEntity<>(trades,HttpStatus.OK);
    }

    @GetMapping(path="/trades/{tradeId}")
    public ResponseEntity<StockTrade> getTrade(@PathVariable Integer tradeId){
        if(stockTradeImpl.isExists(tradeId)){
            StockTrade trade = stockTradeImpl.findTradeById(tradeId);
            System.out.println("trade found: " + tradeId);
            return new ResponseEntity<>(trade,HttpStatus.OK);
        }else{
            System.out.println("trade not found: " + tradeId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping(path="/trades/{tradeId}")
    @PutMapping(path="/trades/${tradeId}")
    @PatchMapping(path="/trades/${tradeId}")
    public ResponseEntity<StockTrade> unsupportedRequest(@PathVariable Integer tradeId){
        System.out.println("unsupported request");
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);

    }
}
