package br.com.rafael.crudofusers.domain.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private String username;
    private String email;
    private String password;
}
