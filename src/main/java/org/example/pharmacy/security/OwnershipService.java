package org.example.pharmacy.security;

import org.example.pharmacy.persistance.entities.AuthEntity;
import org.example.pharmacy.persistance.repository.AuthRepository;
import org.example.pharmacy.services.userService.UserService;
import org.example.pharmacy.services.userService.error.UserWithThisUsernameNotFoundException;

public abstract class OwnershipService {
    protected AuthRepository authRepository;

    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }
    public boolean isOwner(String username, Long userId) {
        if(userId==null||username==null){
            return false;
        }

        AuthEntity authEntity = authRepository.findByUsername(username).orElseThrow(()-> UserWithThisUsernameNotFoundException.create(username));

        return userId==authEntity.getUser().getId();

    }
}
