package ro.project.parts.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.project.parts.manager.domain.Part;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockDto {
    Long id;

    @Positive(message = "Quantity needs to be positive")
    @Min(message = "Quantity must be bigger then 0", value = 0)
    private BigDecimal quantity;

    @Size(message = "The maximal length of the lot must be 10 characters", max = 10)
    private String lot;

    private LocalDateTime createdAt;

    private Part part;

    public StockDto(Long id, BigDecimal quantity, String lot, LocalDateTime createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.lot = lot;
        this.createdAt = createdAt;
    }
}
