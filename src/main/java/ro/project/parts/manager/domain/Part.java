package ro.project.parts.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Table(name = "parts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Part {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull (message = "You need to insert the name of the part")
    @Column
    @Size(message = "The maximal length of the name must be 64 characters", max = 64)
    private String name;

    @NotNull (message = "You need to insert the part number")
    @Column
    @Size(message = "The maximal length of the part number must be 10 characters", max = 10)
    private String partNumber;

    @NotNull (message = "You need to insert the supplier")
    @Column
    @Size(message = "The maximal length of the supplier name must be 64 characters", max = 64)
    private String supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Stock> stocks;

    public Part(String name, String partNumber, String supplier) {
        this.name = name;
        this.partNumber = partNumber;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
