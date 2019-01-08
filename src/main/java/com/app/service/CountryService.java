package com.app.service;

import com.app.dto.CountryDto;
import com.app.dto.MyModelMapper;
import com.app.exceptions.MyException;
import com.app.model.Country;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;

import java.time.LocalDateTime;

public class CountryService {
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addCountry(CountryDto countryDto) {
        try {
            Country country = modelMapper.fromCountryDtoToCountry(countryDto);

            if (isCountry(country.getName())) {
                throw new MyException("COUNTRY ALREADY IN DB", LocalDateTime.now());
            }

            if (country.getName() == null) {
                throw new NullPointerException();
            }

            countryRepository.addOrUpdate(country);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD COUNTRY", LocalDateTime.now());
        }
    }

    private boolean isCountry(String name) {
        return countryRepository.findByName(name).isPresent();
    }

    public void newCountry(String name) {
        addCountry(CountryDto.builder()
                .name(name)
                .build());
    }

    public void deleteCountry(String name) {
        countryRepository.delete(countryRepository
                .findByName(name)
                .orElseThrow(NullPointerException::new)
                .getId());
    }

    public void updateCountry(String name, String newName) {
        Country country = countryRepository.findByName(name).orElseThrow(NullPointerException::new);
        country.setName(newName);
        countryRepository.addOrUpdate(country);
    }

    public void showAll() {
        countryRepository.findAll().forEach(
                country -> System.out.println(
                        "COUNTRY: id: " + country.getId() +
                                ", name: " + country.getName()));
    }
}
