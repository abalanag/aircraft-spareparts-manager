package ro.project.parts.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.project.parts.manager.domain.Stock;
import ro.project.parts.manager.model.StockDto;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Transactional
    @Modifying
    @Query("update Stock p set p.quantity = :pQuantity + p.quantity where p.lot = :pLot")
    void addStock(@Param("pLot") String name,
                 @Param("pQuantity") BigDecimal quantity);

    @Query("Select new ro.project.parts.manager.model.StockDto(p.id, p.quantity, p.lot, p.createdAt, p.part)" +
            "from Stock p where p.lot = :pLot")
    Optional<StockDto> retrieveByLot(@Param("pLot") String lot);

    @Transactional
    @Modifying
    @Query("update Stock p set p.quantity = p.quantity - :pQuantity where p.lot = :pLot")
    void removePart(@Param("pLot") String name,
                    @Param("pQuantity") BigDecimal quantity);

    Stock findByLot(String Lot);
}
