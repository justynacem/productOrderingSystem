package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.TradeDto;
import com.app.exceptions.MyException;
import com.app.model.Trade;
import com.app.repository.TradeRepository;
import com.app.repository.TradeRepositoryImpl;

import java.time.LocalDateTime;

public class TradeService {
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addTrade(TradeDto tradeDto) {
        try {
            Trade trade = modelMapper.fromTradeDtoToTrade(tradeDto);

            if (trade.getName() == null) {
                throw new NullPointerException();
            }

            if (isTrade(trade.getName())) {
                throw new MyException("TRADE ALREADY IN DB", LocalDateTime.now());
            }

            if (tradeRepository.findByName(trade.getName()).isPresent()) {
            }

            tradeRepository.addOrUpdate(trade);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD TRADE", LocalDateTime.now());
        }
    }

    private boolean isTrade(String name) {
        return tradeRepository.findByName(name).isPresent();
    }

    public void newTrade(String name) {
        addTrade(TradeDto.builder()
                .name(name)
                .build());
    }

    public void deleteTrade(String name) {
        tradeRepository.delete(tradeRepository
                .findByName(name)
                .orElseThrow(NullPointerException::new).getId());
    }

    public void updateTrade() {

    }

    public void showAll() {
        tradeRepository.findAll().forEach(
                trade -> System.out.println(
                        trade.getId() + trade.getName()
                )
        );
    }
}
