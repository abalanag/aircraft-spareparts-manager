package ro.project.parts.manager.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.project.parts.manager.domain.Part;
import ro.project.parts.manager.model.PartDto;
import ro.project.parts.manager.service.PartService;

import javax.validation.Valid;

@Api(value = "API for handling packages")
@RestController
public class PartManagerController {

    @Autowired
    private PartService partService;

    @ApiOperation(value = "Endpoint that will save a new Part")
    @PostMapping(value = "/parts/new-part/", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Part> addNewPart(@Valid @RequestBody final PartDto partDto) {
        return new ResponseEntity<>(partService.addNewPart(partDto), HttpStatus.CREATED);
    }
}
