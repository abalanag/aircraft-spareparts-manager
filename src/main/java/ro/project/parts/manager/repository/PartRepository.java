package ro.project.parts.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.project.parts.manager.domain.Part;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findByName(String name);
}
