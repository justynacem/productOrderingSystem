package com.app.service;

import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.dto.ProducerDto;
import com.app.exceptions.MyException;
import com.app.model.Country;
import com.app.model.Producer;
import com.app.model.Trade;
import com.app.repository.*;

import java.time.LocalDateTime;

public class ProducerService {
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private ProducerRepository producerRepository = new ProducerRepositoryImpl();
    private TradeRepository tradeRepository = new TradeRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addProducer(ProducerDto producerDto) {
        try {
            Producer producer = modelMapper.fromProducerDtoToProducer(producerDto);

            if (isProducer(producer.getName(), producer.getCountry().getName(), producer.getTrade().getName())) {
                throw new MyException("PRODUCER ALREADY IN DB", LocalDateTime.now());
            }

            if (producer.getTrade() == null) {
                throw new NullPointerException();
            }

            Trade trade
                    = tradeRepository
                    .findByName(producer.getTrade().getName())
                    .orElseThrow(NullPointerException::new);

            producer.setTrade(trade);

            if (producer.getCountry() == null) {
                throw new NullPointerException();
            }

            Country country
                    = countryRepository
                    .findByName(producer.getCountry().getName())
                    .orElseThrow(NullPointerException::new);

            producer.setCountry(country);

            producerRepository.addOrUpdate(producer);
        } catch (Exception e) {
            throw new MyException("SERVICE, ADD PRODUCER", LocalDateTime.now());
        }
    }

    private boolean isProducer(String name, String country, String trade) {
        return producerRepository.findByNameCountryTrade(name, country, trade).isPresent();
    }

    public void newProducer(DataManager dataManager) {
        addProducer(ProducerDto.builder()
                .name(dataManager.getName())
                .countryDto(modelMapper.fromCountryToCountryDto(countryRepository.findByName(dataManager.getCountry())
                        .orElseThrow(MyException::new)))
                .tradeDto(modelMapper.fromTradeToTradeDto(tradeRepository.findByName(dataManager.getTrade())
                        .orElseThrow(MyException::new)))
                .build());
    }

    public void deleteProducer(String name, String country, String trade) {
        producerRepository.delete(producerRepository
                .findByNameCountryTrade(name, country, trade)
                .orElseThrow(NullPointerException::new).getId());
    }

    public void updateProducer() {

    }

    public void showAll() {
        producerRepository.findAll().forEach(
                producer -> System.out.println(
                        "PRODUCER: \t" + producer.getName() +
                                " COUNTRY: \t" + producer.getCountry() +
                                " TRADE: \t" + producer.getTrade()
                )
        );
    }
}
