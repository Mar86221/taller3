package org.luismore.taller3.controllers;

import org.luismore.taller3.domain.dtos.*;
import org.luismore.taller3.domain.entities.Token;
import org.luismore.taller3.domain.entities.User;
import org.luismore.taller3.services.UserService;
import jakarta.validation.Valid;
import org.luismore.taller3.domain.entities.User;
import org.luismore.taller3.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/login")
//    public ResponseEntity<GeneralResponse> login(@RequestBody @Valid UserLoginDTO info) {
//        User user = userService.findByUsernameOrEmail(info.getIdentifier(), info.getIdentifier());
//
//        if (user == null) {
//            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
//        }
//
//        if (userService.checkPassword(user, info.getPassword())) {
//            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//        }
//
//        return GeneralResponse.getResponse(HttpStatus.OK, "Login successful");
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@ModelAttribute @Valid UserLoginDTO info, BindingResult validations){
        //AQUI HICIMOS EL REFACTOR Y COMPLEMENTAMOS EN EL ESPACIO DE LOS ... CON LO QUE YA TENIAMOS
        User user = userService.findByUsernameOrEmail(info.getIdentifier(), info.getIdentifier());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            Token token = userService.registerToken(user);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<GeneralResponse> register(@RequestBody @Valid UserRegisterDTO info) {
        User user = userService.findByUsernameOrEmail(info.getUsername(), info.getEmail());

        if (user != null) {
            return GeneralResponse.getResponse(HttpStatus.CONFLICT, "User already exists");
        }

        userService.register(info);

        return GeneralResponse.getResponse(HttpStatus.CREATED, "User registered successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<GeneralResponse> getAllUsers() {
        List<UserResponseDTO> users = userService.findAll();

        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "No users found");
        }

        return GeneralResponse.getResponse(HttpStatus.OK, userService.findAll());
    }

    @PostMapping("/update")
    public ResponseEntity<GeneralResponse> updateUser(@RequestBody @Valid ChangePasswordDTO info) {
        User user = userService.findByIdentifier(info.getIdentifier());

        if (user == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        if (userService.checkPassword(user, info.getOldPassword())) {
            return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        userService.changePassword(info);

        return GeneralResponse.getResponse(HttpStatus.OK, "User updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponse> deleteUser(@PathVariable UUID id) {
        if (userService.findByUUID(id) == null) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found");
        }

        userService.deleteUser(id);

        return GeneralResponse.getResponse(HttpStatus.OK, "User deleted successfully");
    }
}
