package ro.project.parts.manager.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.project.parts.manager.CustomExceptions.PartAlreadyExistException;
import ro.project.parts.manager.domain.Part;
import ro.project.parts.manager.model.PartDto;
import ro.project.parts.manager.repository.PartRepository;
import ro.project.parts.manager.service.PartServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PartServiceImplTest {


    @Mock
    private PartRepository partRepository;

    @InjectMocks
    private PartServiceImpl partService;

    @Test
    void addNewPartTest() {

        PartDto existingPart = new PartDto("Part", "1234", "Supplier");
        Part existingPartRepository = new Part("Part", "1234", "Supplier");
        PartDto newPart = new PartDto("PartNew", "1234", "Supplier");
        Part expectedPart = new Part("PartNew", "1234", "Supplier");

        when(partRepository.findByName("Part")).thenReturn(Optional.of(existingPartRepository), Optional.of(expectedPart));

        assertThrows(PartAlreadyExistException.class, () -> partService.addNewPart(existingPart));
        assertEquals(newPart.getId(), partService.addNewPart(newPart));
    }

    @Test
    void addNewPartTest2() {

        PartDto newPart = new PartDto("newPart", "1234", "Supplier");
        partService.addNewPart(newPart);
    }
}