package com.app.service;

import com.app.dto.DataManager;
import com.app.dto.MyModelMapper;
import com.app.dto.StockDto;
import com.app.exceptions.MyException;
import com.app.model.Product;
import com.app.model.Shop;
import com.app.model.Stock;
import com.app.repository.*;

import java.time.LocalDateTime;

public class StockService {
    private ProductRepository productRepository = new ProductRepositoryImpl();
    private ShopRepository shopRepository = new ShopRepositoryImpl();
    private StockRepository stockRepository = new StockRepositoryImpl();
    private MyModelMapper modelMapper = new MyModelMapper();

    private void addStock(StockDto stockDto) {
        try {
            Stock stock = modelMapper.fromStockDtoToStock(stockDto);

            if (isStock(stock.getProduct().getId(), stock.getShop().getId())) {
                Stock stock1 = stockRepository
                        .findByProductAndShop(stock.getProduct().getId(), stock.getShop().getId())
                        .orElseThrow(NullPointerException::new);
                stock1.setQuantity(stock1.getQuantity() + stock.getQuantity());
                stockRepository.addOrUpdate(stock1);
            } else {
                if (stock.getProduct() == null) {
                    throw new NullPointerException();
                }

                Product product
                        = productRepository
                        .findByName(stock.getProduct().getName())
                        .orElseThrow(NullPointerException::new);

                stock.setProduct(product);

                if (stock.getShop() == null) {
                    throw new NullPointerException();
                }

                Shop shop
                        = shopRepository
                        .findByName(stock.getShop().getName())
                        .orElseThrow(NullPointerException::new);

                stock.setShop(shop);

                if (stock.getQuantity() <= 0) {
                    throw new NullPointerException();
                }

                stockRepository.addOrUpdate(stock);
            }


        } catch (Exception e) {
            throw new MyException("SERVICE, ADD STOCK", LocalDateTime.now());
        }
    }

    private boolean isStock(Long productId, Long shopId) {
        return stockRepository.findByProductAndShop(productId, shopId).isPresent();
    }

    public void newStock(DataManager dataManager) {

        addStock(StockDto.builder()
                .productDto(modelMapper.fromProductToProductDto(productRepository
                        .findByName(dataManager.getProduct()).orElseThrow(NullPointerException::new)))
                .shopDto(modelMapper.fromShopToShopDto(shopRepository
                        .findByName(dataManager.getShop()).orElseThrow(NullPointerException::new)))
                .quantity(dataManager.getQuantity())
                .build());
    }

    public void deleteStock(String product, String shop) {
        stockRepository.delete(stockRepository.findByProductAndShop(
                productRepository.findByName(product).orElseThrow(NullPointerException::new).getId(),
                shopRepository.findByName(shop).orElseThrow(NullPointerException::new).getId()
        ).orElseThrow(NullPointerException::new).getId());
    }

    public void updateStock() {

    }

    public void showAll() {
        stockRepository.findAll().forEach(
                stock -> System.out.println(
                        "SHOP: \t" + stock.getShop() +
                                " PRODUCT: \t" + stock.getProduct() +
                                " QUANTITY: \t" + stock.getQuantity()
                )
        );
    }
}
