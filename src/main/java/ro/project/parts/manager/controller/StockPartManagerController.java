package ro.project.parts.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.project.parts.manager.model.StockDto;
import ro.project.parts.manager.repository.StockRepository;
import ro.project.parts.manager.service.StockService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Api(value = "API for handling stocks")
@RestController
public class StockPartManagerController {

    @Autowired
    private StockService sparePartService;

    @Autowired
    private StockRepository stockRepository;

    @ApiOperation(value = "Endpoint which returns a paginated page with all the parts in stock, receive a parameter for page number and the number of items that is shown on a page")
    @GetMapping(value = "/stocks/stock", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<StockDto>> findPack(@RequestParam("page") final Integer start, @RequestParam("size") final Integer size) {
        return new ResponseEntity<>(sparePartService.findAll(start, size), HttpStatus.OK);
    }

    @ApiOperation(value = "Endpoint that is able to update the quantity of an existed lot")
    @PostMapping(value = "/stocks/update-stock/{partLot}/{quantity}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StockDto> updateStock(@Valid @PathVariable("partLot") final String name,
                                                @Valid @PathVariable("quantity") final BigDecimal quantity) {
        return ResponseEntity.ok(sparePartService.addStock(name, quantity));
    }

    @ApiOperation(value = "Endpoint which consume a specified quantity from a stock, if the quantity in stock is 0, the lot will be removed completely")
    @PostMapping(value = "/stocks/consume-stock/", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> removeQuantityFromStock(@Valid @RequestBody final StockDto stockDto) {
        final StockDto stock = sparePartService.removeQuantityFromStock(stockDto);
        if (stock.getQuantity() != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Requested quantity was removed, remaining quantity: " + stock.getQuantity());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("All quantity was used, the part was removed from stock");
        }
    }

    @ApiOperation(value = "Endpoint which saves a new lot for an existent item. If the item doesn't exist, PartNotFondException will be thrown.")
    @PostMapping(value = "/stocks/add-new-stock/", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StockDto> addNewPart(@Valid @RequestBody final StockDto stockDto) {
        return new ResponseEntity<>(sparePartService.addNewPartInStock(stockDto), HttpStatus.CREATED);
    }
}
