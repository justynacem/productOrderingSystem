package com.app.service;

import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.dto.ShopDto;
import com.app.exceptions.MyException;
import com.app.model.Country;
import com.app.model.Shop;
import com.app.repository.CountryRepository;
import com.app.repository.CountryRepositoryImpl;
import com.app.repository.ShopRepository;
import com.app.repository.ShopRepositoryImpl;

import java.time.LocalDateTime;

public class ShopService {
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private CountryRepository countryRepository = new CountryRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addShop(ShopDto shopDto) {
        try {
            Shop shop = modelMapper.fromShopDtoToShop(shopDto);

            if (isShop(shop.getName(), shop.getCountry().getName())) {
                throw new MyException("SHOP ALREADY IN DB", LocalDateTime.now());
            }

            if (shop.getCountry() == null) {
                throw new NullPointerException();
            }

            Country country
                    = countryRepository
                    .findByName(shop.getCountry().getName())
                    .orElseThrow(NullPointerException::new);

            shop.setCountry(country);
            shopRepository.addOrUpdate(shop);

        } catch (Exception e) {
            throw new MyException("SERVICE, ADD SHOP", LocalDateTime.now());
        }
    }

    private boolean isShop(String name, String country) {
        return shopRepository.findByNameAndCountry(name, country).isPresent();
    }

    public void newShop(DataManager dataManager) {
        addShop(ShopDto.builder()
                .name(dataManager.getShop())
                .countryDto(modelMapper.fromCountryToCountryDto(countryRepository.findByName(dataManager.getCountry())
                        .orElseThrow(MyException::new)))
                .build());
    }

    public void deleteShop(String name, String country) {
        shopRepository.delete(shopRepository
                .findByNameAndCountry(name, country)
                .orElseThrow(NullPointerException::new)
                .getId());
    }

    public void updateShop() {

    }

    public void showAll() {
        shopRepository.findAll().forEach(
                shop -> System.out.println(
                        "SHOP: \t" + shop.getName() + " from: " + shop.getCountry().getName()
                )
        );
    }
}
