package ro.project.parts.manager.service;

import ro.project.parts.manager.model.StockDto;

import java.math.BigDecimal;
import java.util.List;

public interface StockService {

    List<StockDto> findAll(Integer page, Integer size);

    StockDto addStock(String name, BigDecimal quantity);

    StockDto removeQuantityFromStock(StockDto stockDto);

    StockDto addNewPartInStock(StockDto stockDto);

}
