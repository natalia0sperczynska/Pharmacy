package org.example.pharmacy.services.purchaseService;

import org.example.pharmacy.controllers.DTO.purchaseDTO.*;
import org.example.pharmacy.controllers.DTO.userDTO.GetUserDTO;
import org.example.pharmacy.persistance.entities.*;
import org.example.pharmacy.persistance.repository.AuthRepository;
import org.example.pharmacy.persistance.repository.MedsRepository;
import org.example.pharmacy.persistance.repository.PurchaseRepository;
import org.example.pharmacy.persistance.repository.UserRepository;
import org.example.pharmacy.security.OwnershipService;
import org.example.pharmacy.services.purchaseService.error.QuantityException;
import org.example.pharmacy.services.purchaseService.error.MedNotFoundException;
import org.example.pharmacy.services.purchaseService.error.PurchaseNotFoundException;
import org.example.pharmacy.services.purchaseService.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PurchaseService extends OwnershipService {
    private final PurchaseRepository purchaseRepository;
    private final MedsRepository medsRepository;
    private final UserRepository userRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository, MedsRepository medsRepository, UserRepository userRepository, AuthRepository authRepository) {
        super(authRepository);
        this.purchaseRepository = purchaseRepository;
        this.medsRepository = medsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or isAuthenticated() and this.isOwner(authentication.name, #addPurchaseDTO.userId)")
    public AddPurchaseResponseDTO create(AddPurchaseDTO addPurchaseDTO) {
        UserEntity user = userRepository.findById(addPurchaseDTO.getUserId())
                .orElseThrow(() -> UserNotFoundException.create(addPurchaseDTO.getUserId()));

        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setUser(user);
        purchase.setPurchaseDate(addPurchaseDTO.getPurchaseDate());
        purchase.setPaymentMethod(addPurchaseDTO.getPaymentMethod());

        for (PurchaseItemDTO itemDTO : addPurchaseDTO.getItems()) {
            MedEntity med = medsRepository.findById(itemDTO.getMedId())
                    .orElseThrow(() -> MedNotFoundException.create(itemDTO.getMedId()));

            if (med.getQuantity() < itemDTO.getQuantity()) {
                throw QuantityException.create(med.getName(), med.getQuantity());
            }

            med.setQuantity(med.getQuantity() - itemDTO.getQuantity());

            PurchaseItemEntity purchaseItem = new PurchaseItemEntity();
            purchaseItem.setPurchase(purchase);
            purchaseItem.setMed(med);
            purchaseItem.setQuantity(itemDTO.getQuantity());
            purchaseItem.setUnitPrice(med.getPrice());
            purchase.getItems().add(purchaseItem);
        }

        PurchaseEntity savedPurchase = purchaseRepository.save(purchase);
        return mapToAddPurchaseResponseDTO(savedPurchase);
    }

    @PostAuthorize("hasRole('ADMIN') or this.isOwner(authentication.name, returnObject.getUser().getId())")
    public GetPurchaseResponseDTO getPurchaseById(long id) {
        PurchaseEntity purchaseEntity = purchaseRepository.findById(id).orElseThrow(() -> PurchaseNotFoundException.create(id));
        return mapPurchaseToGetResponseDTO(purchaseEntity);
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == null or this.isOwner(authentication.name, #userId)")
    public List<GetPurchaseResponseDTO> getAllPurchases(Long userId) {
        List<PurchaseEntity> purchases;
        if (userId == null) {
            purchases = purchaseRepository.findAll();
        } else {
            purchases = purchaseRepository.findByUserId(userId);
        }
        return purchases.stream().map(this::mapPurchaseToGetResponseDTO).collect(Collectors.toList());
    }

    private GetPurchaseResponseDTO mapPurchaseToGetResponseDTO(PurchaseEntity purchase) {
        GetUserDTO user = new GetUserDTO(
                purchase.getUser().getId(),
                purchase.getUser().getName(),
                purchase.getUser().getLastName(),
                purchase.getUser().getEmail(),
                purchase.getUser().getPhoneNumber()
        );

        List<PurchaseItemResponseDTO> items = purchase.getItems().stream()
                .map(item -> new PurchaseItemResponseDTO(
                        item.getMed().getId(),
                        item.getMed().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getUnitPrice() * item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new GetPurchaseResponseDTO(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getPaymentMethod(),
                user,
                items
        );
    }

    private AddPurchaseResponseDTO mapToAddPurchaseResponseDTO(PurchaseEntity purchase) {
        List<PurchaseItemResponseDTO> items = purchase.getItems().stream()
                .map(item -> new PurchaseItemResponseDTO(
                        item.getMed().getId(),
                        item.getMed().getName(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getUnitPrice() * item.getQuantity()
                ))
                .collect(Collectors.toList());

        return new AddPurchaseResponseDTO(
                purchase.getId(),
                purchase.getPurchaseDate(),
                purchase.getPaymentMethod(),
                purchase.getUser().getId(),
                items
        );
    }
}