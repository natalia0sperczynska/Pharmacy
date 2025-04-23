package org.example.pharmacy.persistance.repository;

import org.example.pharmacy.persistance.entities.MedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MedsRepository extends JpaRepository<MedEntity, Long> {
//MedEntity findTheSameMed(
//            long barcode, String name, String dose, String companyName, Float price, String form, java.sql.Date expiryDate);
}
