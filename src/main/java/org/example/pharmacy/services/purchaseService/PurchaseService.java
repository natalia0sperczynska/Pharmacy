package org.example.pharmacy.services.purchaseService;

import org.example.pharmacy.controllers.DTO.medDTO.GetMedDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.AddPurchaseResponseDTO;
import org.example.pharmacy.controllers.DTO.purchaseDTO.GetPurchaseResponseDTO;
import org.example.pharmacy.controllers.DTO.userDTO.GetUserDTO;
import org.example.pharmacy.persistance.entities.MedEntity;
import org.example.pharmacy.persistance.entities.PurchaseEntity;
import org.example.pharmacy.persistance.entities.UserEntity;
import org.example.pharmacy.persistance.repository.MedsRepository;
import org.example.pharmacy.persistance.repository.PurchaseRepository;
import org.example.pharmacy.persistance.repository.UserRepository;
import org.example.pharmacy.services.medsService.error.MedNotFound;
import org.example.pharmacy.services.purchaseService.error.MedNotFoundException;
import org.example.pharmacy.services.purchaseService.error.PurchaseNotFoundException;
import org.example.pharmacy.services.purchaseService.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final MedsRepository medsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, MedsRepository medsRepository, UserRepository userRepository) {
        this.purchaseRepository = purchaseRepository;
        this.medsRepository = medsRepository;
        this.userRepository = userRepository;
    }
    public AddPurchaseResponseDTO create(AddPurchaseDTO addPurchaseDTO) {

        UserEntity user = userRepository.findById(addPurchaseDTO.getUserId()).orElseThrow(() -> UserNotFoundException.create(addPurchaseDTO.getUserId()));

        MedEntity med = medsRepository.findById(addPurchaseDTO.getMedId()).orElseThrow(() -> MedNotFoundException.create(addPurchaseDTO.getMedId()));

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setMed(med);
        purchase.setUser(user);
        purchase.setPurchaseDate(new Date(System.currentTimeMillis()));
        purchase.setPaymentMethod(addPurchaseDTO.getPaymentMethod());
        var newPurchase = purchaseRepository.save(purchase);
        purchaseRepository.save(purchase);
        return new AddPurchaseResponseDTO(
                newPurchase.getId(),
                newPurchase.getPurchaseDate(),
                newPurchase.getPaymentMethod(),
                newPurchase.getUser().getId(),
                newPurchase.getMed().getId()
        );
    }
    public GetPurchaseResponseDTO getPurchaseById(long id) {
        PurchaseEntity purchaseEntity=  purchaseRepository.findById(id).orElseThrow(()-> PurchaseNotFoundException.create(id));
        return mapPurchase(purchaseEntity);
    }

    public List<GetPurchaseResponseDTO> getAllPurchases() {
        List<PurchaseEntity> purchaseEntity=  purchaseRepository.findAll();
        return  purchaseEntity.stream().map(this::mapPurchase).collect(Collectors.toList());

    }

    private GetPurchaseResponseDTO mapPurchase(PurchaseEntity purchaseEntity) {
        GetUserDTO user = new GetUserDTO(purchaseEntity.getUser().getId(),purchaseEntity.getUser().getName(),purchaseEntity.getUser().getLastName(),purchaseEntity.getUser().getEmail());
        GetMedDTO med = new GetMedDTO(purchaseEntity.getMed().getId(),purchaseEntity.getMed().getBarcode(),purchaseEntity.getMed().getName(),purchaseEntity.getMed().getDose(),purchaseEntity.getMed().getCompany_name(),purchaseEntity.getMed().getPrice(),purchaseEntity.getMed().getForm(),purchaseEntity.getMed().getExpiry_date(),purchaseEntity.getMed().getQuantity());
        return new GetPurchaseResponseDTO(purchaseEntity.getId(),purchaseEntity.getPurchaseDate(),purchaseEntity.getPaymentMethod(),user,med);
    }
}

