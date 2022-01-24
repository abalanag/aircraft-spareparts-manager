/*
package ro.project.parts.manager.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ro.project.parts.manager.CustomExceptions.PartNotFoundException;
import ro.project.parts.manager.model.StockDto;
import ro.project.parts.manager.repository.StockRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNegativeValueValidator implements ConstraintValidator<NotNegativeValue, StockDto> {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void initialize(NotNegativeValue constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(StockDto part, ConstraintValidatorContext context) {
        if (stockRepository.findByName(part.getName()).get() != null) {
            return stockRepository.findByName(part.getName()).get().getQuantity().compareTo(part.getQuantity()) >= 0;
        } else {
            throw new PartNotFoundException("Part name " + part.getName() + " doesn't exist");
        }
    }
}
*/