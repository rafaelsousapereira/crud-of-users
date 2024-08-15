package br.com.rafael.crudofusers.domain.controller;

import br.com.rafael.crudofusers.domain.dto.UserDTO;
import br.com.rafael.crudofusers.domain.model.UserEntity;
import br.com.rafael.crudofusers.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        var userEntity = convertEntityToDTO(userDTO);
        this.userService.create(userEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> findAll() {
        var users = this.userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Optional<UserEntity>> findById(@PathVariable UUID id) {
        var user = this.userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    private static UserEntity convertEntityToDTO(UserDTO userDTO) {
        return UserEntity.builder()
            .name(userDTO.getName())
            .username(userDTO.getUsername())
            .email(userDTO.getEmail())
            .password(userDTO.getPassword())
        .build();
    }
}
