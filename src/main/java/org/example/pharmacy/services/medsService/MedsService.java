package org.example.pharmacy.services.medsService;
import org.example.pharmacy.controllers.DTO.medDTO.AddMedDTO;
import org.example.pharmacy.controllers.DTO.medDTO.AddMedResponseDTO;
import org.example.pharmacy.controllers.DTO.medDTO.GetMedDTO;
import org.example.pharmacy.persistance.entities.MedEntity;
import org.example.pharmacy.persistance.repository.MedsRepository;
import org.example.pharmacy.services.purchaseService.error.MedNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedsService {
    private final MedsRepository medsRepository;

    @Autowired
    public MedsService(MedsRepository medsRepository) {
        this.medsRepository = medsRepository;
    }
    public List<GetMedDTO> getAll() {
        var meds = medsRepository.findAll();
        return meds.stream().map((med) -> new GetMedDTO(med.getId(), med.getBarcode(), med.getName(), med.getDose(), med.getCompany_name(), med.getPrice(), med.getForm(), med.getExpiry_date(), med.getQuantity())).collect(Collectors.toList());
    }
    public GetMedDTO getOne(long id) {
        var med = medsRepository.findById(id).orElseThrow(() -> MedNotFoundException.create(id));
        return new GetMedDTO(med.getId(), med.getBarcode(), med.getName(), med.getDose(), med.getCompany_name(), med.getPrice(), med.getForm(), med.getExpiry_date(), med.getQuantity());
    }
    public AddMedResponseDTO create(AddMedDTO med) {
       var medEntity = new MedEntity();
       var price = med.getPrice();
       var roundPrice = Math.round(price*100.0) / 100.0f;
       medEntity.setPrice(roundPrice);
       medEntity.setDose(med.getDose());
       medEntity.setForm(med.getForm());
       medEntity.setExpiry_date(med.getExpiry_date());
       medEntity.setCompany_name(med.getCompany_name());
       medEntity.setName(med.getName());
       medEntity.setBarcode(med.getBarcode());
       medEntity.setQuantity(med.getQuantity());

       var newMed = medsRepository.save(medEntity);

       return new AddMedResponseDTO(newMed.getId(),newMed.getBarcode(), newMed.getName(), newMed.getDose(), newMed.getCompany_name(), newMed.getPrice(), newMed.getForm(), newMed.getExpiry_date(), newMed.getQuantity());
    }

    public void delete(long id) {
//        if(!medsRepository.existsById(id)){
//            throw new RuntimeException();
//        }
        medsRepository.deleteById(id);
    }
}
