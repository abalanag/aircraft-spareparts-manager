package ro.project.parts.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.parts.manager.CustomExceptions.PartAlreadyExistException;
import ro.project.parts.manager.domain.Part;
import ro.project.parts.manager.model.PartDto;
import ro.project.parts.manager.repository.PartRepository;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Override
    public Part addNewPart(final PartDto part) {

        partRepository.findByName(part.getName()).ifPresent(a -> {
            throw new PartAlreadyExistException();
        });
        return partRepository.findByName(part.getName()).orElse(partRepository.save(new Part(part.getName(), part.getPartNumber(), part.getSupplier())));
    }
}
