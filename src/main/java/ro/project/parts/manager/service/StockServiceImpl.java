package ro.project.parts.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ro.project.parts.manager.CustomExceptions.PartAlreadyExistException;
import ro.project.parts.manager.CustomExceptions.PartNotFoundException;
import ro.project.parts.manager.CustomExceptions.QuantityToBigException;
import ro.project.parts.manager.domain.Stock;
import ro.project.parts.manager.model.StockDto;
import ro.project.parts.manager.repository.PartRepository;
import ro.project.parts.manager.repository.StockRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository stockRepository;

    @Autowired
    PartRepository partRepository;

    @Override
    public List<StockDto> findAll(final Integer offset, final Integer size) {
        return stockRepository.findAll(Pageable.ofSize(size).withPage(offset / size))
                .stream()
                .map(s -> new StockDto(s.getId(), s.getQuantity(), s.getLot(), s.getCreatedAt(), s.getPart()))
                .collect(Collectors.toList());
    }

    @Override
    public StockDto addStock(final String lot, final BigDecimal quantity) {
        stockRepository.retrieveByLot(lot).ifPresentOrElse(
                a -> stockRepository.addStock(lot, quantity),
                () -> {
                    throw new PartNotFoundException("There is no part with this lot");
                });

        return stockRepository.retrieveByLot(lot).orElseThrow(PartNotFoundException::new);
    }

    @Override
    public StockDto removeQuantityFromStock(final StockDto stockDto) {
        stockRepository.retrieveByLot(stockDto.getLot()).ifPresentOrElse(s -> {
            if (s.getQuantity().compareTo(stockDto.getQuantity()) > 0) {
                stockRepository.removePart(stockDto.getLot(), stockDto.getQuantity());
            } else if (s.getQuantity().compareTo(stockDto.getQuantity()) == 0) {
                stockRepository.delete(stockRepository.findByLot(stockDto.getLot()));
            } else {
                throw new QuantityToBigException();
            }
        }, () -> {
            throw new PartNotFoundException("There is no part with this lot");
        });

        return stockRepository.retrieveByLot(stockDto.getLot()).orElse(new StockDto());
    }

    @Override
    public StockDto addNewPartInStock(final StockDto stockDto) {
        stockRepository.retrieveByLot(stockDto.getLot())
                .ifPresent(a -> {throw new PartAlreadyExistException("There is already a part with this lot");});

        final Stock stock = Optional.ofNullable(stockRepository.findByLot(stockDto.getLot())).orElse(stockRepository.save(
                new Stock(stockDto.getQuantity(),
                        stockDto.getLot(),
                        stockDto.getCreatedAt(),
                        partRepository.findByName(stockDto.getPart().getName()).orElseThrow(() -> new PartNotFoundException("The part you inserted doesn't exist")))));

        return new StockDto(stock.getId(), stock.getQuantity(), stock.getLot(), stock.getCreatedAt(), stock.getPart());
    }
}
