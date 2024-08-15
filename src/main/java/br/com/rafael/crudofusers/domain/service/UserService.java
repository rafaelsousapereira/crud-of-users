package br.com.rafael.crudofusers.domain.service;

import br.com.rafael.crudofusers.domain.exception.UserNotFoundException;
import br.com.rafael.crudofusers.domain.model.UserEntity;
import br.com.rafael.crudofusers.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


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

    public void update(UUID id, UserEntity userEntity) {
        var user = this.userRepository.findById(id);

        if (user.isPresent()) {
            this.userRepository.save(userEntity);
        }
    }

    public void delete(UUID id) {
        this.userRepository.deleteById(id);
    }
}

