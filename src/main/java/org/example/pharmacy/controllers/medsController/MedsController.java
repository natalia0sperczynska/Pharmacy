package org.example.pharmacy.controllers.medsController;

import org.example.pharmacy.controllers.DTO.medDTO.AddMedDTO;
import org.example.pharmacy.controllers.DTO.medDTO.AddMedResponseDTO;
import org.example.pharmacy.controllers.DTO.medDTO.GetMedDTO;
import org.example.pharmacy.services.medsService.MedsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//controller cant use any entity, just DTO, repo - just entity, service - both

@RestController
@RequestMapping("api/meds")
@PreAuthorize("hasRole('ADMIN')")

public class MedsController {
    private final MedsService medsService;

    public MedsController(MedsService medsService) {
        this.medsService = medsService;
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public List<GetMedDTO> getAllMeds() {
        return medsService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public GetMedDTO getOne(@PathVariable long id) {
        return medsService.getOne(id);
    }

    @PostMapping()
    public ResponseEntity<AddMedResponseDTO> create(@Validated @RequestBody AddMedDTO med) {
        var newMed = medsService.create(med);
        return new ResponseEntity<AddMedResponseDTO>(newMed, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Validated @PathVariable long id) {
        if (medsService.getOne(id) != null) {
            medsService.delete(id);
            return ResponseEntity.accepted().build();
        } else return ResponseEntity.noContent().build();
    }
}
