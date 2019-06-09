package com.vesta.service;

import com.vesta.controller.view.Token;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getById(Long id);

    List<UserDto> findAll();

    void create(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    void delete (Long id);

    UserDto getByUsername(String username);

    Token login(AccountCredential accountCredential);
}
