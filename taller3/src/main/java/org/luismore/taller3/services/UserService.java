package org.luismore.taller3.services;


import org.luismore.taller3.domain.dtos.UserRegisterDTO;
import org.luismore.taller3.domain.dtos.UserResponseDTO;
import org.luismore.taller3.domain.entities.Token;
import org.luismore.taller3.domain.entities.User;
import org.luismore.taller3.domain.dtos.ChangePasswordDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    User findByIdentifier(String identifier);

    User findByUsernameOrEmail(String username, String email);

    User findByUUID(UUID uuid);

    List<UserResponseDTO> findAll();

    void register(UserRegisterDTO userRegisterDTO);

    void changePassword(ChangePasswordDTO info);

    void deleteUser(UUID uuid);

    boolean checkPassword(User user, String password);


    //Token management
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;

    User findOneByIdentifier(String identifier);
    Optional<User> findUserAuthenticated();

}
