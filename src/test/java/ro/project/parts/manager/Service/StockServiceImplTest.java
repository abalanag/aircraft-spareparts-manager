package ro.project.parts.manager.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ro.project.parts.manager.CustomExceptions.PartAlreadyExistException;
import ro.project.parts.manager.CustomExceptions.PartNotFoundException;
import ro.project.parts.manager.CustomExceptions.QuantityToBigException;
import ro.project.parts.manager.domain.Part;
import ro.project.parts.manager.domain.Stock;
import ro.project.parts.manager.model.StockDto;
import ro.project.parts.manager.repository.PartRepository;
import ro.project.parts.manager.repository.StockRepository;
import ro.project.parts.manager.service.StockServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Mock
    private PartRepository partRepository;

    @Test
    void findAll() {
        List<Stock> stocks = List.of(
                new Stock(1L, BigDecimal.TEN, "12341", LocalDateTime.now(), new Part()),
                new Stock(2L, BigDecimal.TEN, "12342", LocalDateTime.now(), new Part()),
                new Stock(3L, BigDecimal.TEN, "12343", LocalDateTime.now(), new Part()),
                new Stock(4L, BigDecimal.TEN, "12344", LocalDateTime.now(), new Part()),
                new Stock(5L, BigDecimal.TEN, "12345", LocalDateTime.now(), new Part()),
                new Stock(6L, BigDecimal.TEN, "12346", LocalDateTime.now(), new Part()),
                new Stock(7L, BigDecimal.TEN, "12347", LocalDateTime.now(), new Part()),
                new Stock(8L, BigDecimal.TEN, "12348", LocalDateTime.now(), new Part()),
                new Stock(9L, BigDecimal.TEN, "12349", LocalDateTime.now(), new Part()),
                new Stock(10L, BigDecimal.TEN, "123410", LocalDateTime.now(), new Part()));


        //when(stockRepository.findAll()).thenReturn(stocks);


        //var books = stockService.findAll(0, 4);

        //Assertions.assertNotNull(books, "The returned list should exist");
        //Assertions.assertTrue(4 == books.size(), "The returned list doesn't contain the expected number of elements");

    }

    @Test
    void addStock() {

        StockDto existingPart = new StockDto(1L, BigDecimal.TEN, "12341", LocalDateTime.now(), new Part());

        when(stockRepository.retrieveByLot("12341")).thenReturn(Optional.of(existingPart));
        when(stockRepository.findByLot("12341")).thenReturn(new Stock(2L, BigDecimal.valueOf(10), "12342", LocalDateTime.now(), new Part()));

        assertThrows(PartNotFoundException.class, () -> stockService.addStock("unknownPart", BigDecimal.TEN));
        assertEquals((stockService.addStock("12341", BigDecimal.TEN)).getQuantity(), BigDecimal.valueOf(10));
    }

    @Test
    void removeQuantityFromStock() {

        StockDto existingPart = new StockDto(1L, BigDecimal.TEN, "12341", LocalDateTime.now(), new Part());
        StockDto unknownPart = new StockDto(12L, BigDecimal.TEN, "123412", LocalDateTime.now(), new Part());
        StockDto newPartWithBiggerQuantity = new StockDto(1L, BigDecimal.valueOf(25), "12341", LocalDateTime.now(), new Part());

        when(stockRepository.retrieveByLot("12341")).thenReturn(Optional.of(existingPart), Optional.of(newPartWithBiggerQuantity));
        when(stockRepository.findByLot("12341")).thenReturn(new Stock(2L, BigDecimal.valueOf(10), "12342", LocalDateTime.now(), new Part()));

        assertThrows(PartNotFoundException.class, () -> stockService.removeQuantityFromStock(unknownPart));
        assertThrows(QuantityToBigException.class, () -> stockService.removeQuantityFromStock(newPartWithBiggerQuantity));
        assertEquals((stockService.addStock("12341", BigDecimal.TEN)).getQuantity(), BigDecimal.valueOf(25));
    }

    @Test
    void addNewPartInStock() {

        var part = new Part("asd", "asd", "asd");
        var existingPart = new StockDto(1L, BigDecimal.TEN, "12341", LocalDateTime.now(), part);
        var unknownPart = new StockDto(12L, BigDecimal.TEN, "123412", LocalDateTime.now(), part);
        var unknownPartStock = new Stock(12L, BigDecimal.TEN, "123412", LocalDateTime.now(), part);

        when(stockRepository.retrieveByLot("12341")).thenReturn(Optional.of(existingPart));
        assertThrows(PartAlreadyExistException.class, () -> stockService.addNewPartInStock(existingPart));

        when(stockRepository.findByLot("123412")).thenReturn(unknownPartStock);
        when(partRepository.findByName("asd")).thenReturn(Optional.of(part));
        assertEquals(stockService.addNewPartInStock(unknownPart).getId(), unknownPart.getId());
    }
}