package br.com.rafael.crudofusers.domain.service;

import br.com.rafael.crudofusers.domain.exception.UserNotFoundException;
import br.com.rafael.crudofusers.domain.model.UserEntity;
import br.com.rafael.crudofusers.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserEntity create(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<UserEntity> findById(UUID id) {
        var userId = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Id incorrect=[" + id + "]"));
        return Optional.of(userId);
    }

}

