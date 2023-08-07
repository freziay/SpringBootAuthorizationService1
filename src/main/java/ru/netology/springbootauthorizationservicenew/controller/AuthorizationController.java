package ru.netology.springbootauthorizationservicenew.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.springbootauthorizationservicenew.authorities.Authorities;
import ru.netology.springbootauthorizationservicenew.exception.InvalidCredentials;
import ru.netology.springbootauthorizationservicenew.exception.UnauthorizedUser;
import ru.netology.springbootauthorizationservicenew.main.User;
import ru.netology.springbootauthorizationservicenew.service.AuthorizationService;

import java.util.List;


@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid User user) throws InvalidCredentials, UnauthorizedUser {
        return service.getAuthorities(user);
    }
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> icHandler(InvalidCredentials e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> uuHandler(UnauthorizedUser e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
