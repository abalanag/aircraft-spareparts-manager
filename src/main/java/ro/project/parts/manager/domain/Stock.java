package ro.project.parts.manager.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "stocks")
@Entity
public class Stock {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    @Positive(message= "Quantity needs to be positive")
    @Min(message = "Quantity must be bigger then 0", value = 0)
    private BigDecimal quantity;

    @Column
    @Size(message = "The maximal length of the lot must be 10 characters", max = 10)
    private String lot;

    @Column
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;

    public Stock(String name, BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Stock(Long id, String name, BigDecimal quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Stock(BigDecimal quantity, String lot, LocalDateTime createdAt, Part part) {
        this.quantity = quantity;
        this.lot = lot;
        this.createdAt = createdAt;
        this.part = part;
    }
}
