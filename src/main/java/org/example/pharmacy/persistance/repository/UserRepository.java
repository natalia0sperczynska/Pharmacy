package org.example.pharmacy.persistance.repository;

import org.example.pharmacy.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    //Optional<UserEntity> findByUsername(String username);
}
