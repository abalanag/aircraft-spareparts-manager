package ro.project.parts.manager.service;

import ro.project.parts.manager.domain.Part;
import ro.project.parts.manager.model.PartDto;

public interface PartService {

    Part addNewPart(PartDto partDto);

}
