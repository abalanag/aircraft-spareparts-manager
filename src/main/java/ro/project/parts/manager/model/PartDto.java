package ro.project.parts.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.project.parts.manager.domain.Stock;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartDto {

    private Long id;

    @NotNull(message = "You need to insert the name of the part")
    @Size(message = "The maximal length of the name must be 64 characters", max = 64)
    private String name;

    @NotNull(message = "You need to insert the part number")
    @Size(message = "The maximal length of the part number must be 10 characters", max = 10)
    private String partNumber;

    @NotNull(message = "You need to insert the supplier")
    @Size(message = "The maximal length of the supplier name must be 64 characters", max = 64)
    private String supplier;

    private Set<Stock> stocks;

    public PartDto(String name, String part_number, String supplier, Set<Stock> stocks) {
        this.name = name;
        this.partNumber = part_number;
        this.supplier = supplier;
        this.stocks = stocks;
    }

    public PartDto(String name, String partNumber, String supplier) {
        this.name = name;
        this.partNumber = partNumber;
        this.supplier = supplier;
    }
}
