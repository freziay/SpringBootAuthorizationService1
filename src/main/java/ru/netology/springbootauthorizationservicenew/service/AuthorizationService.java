package ru.netology.springbootauthorizationservicenew.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import ru.netology.springbootauthorizationservicenew.authorities.Authorities;
import ru.netology.springbootauthorizationservicenew.exception.InvalidCredentials;
import ru.netology.springbootauthorizationservicenew.exception.UnauthorizedUser;
import ru.netology.springbootauthorizationservicenew.main.User;
import ru.netology.springbootauthorizationservicenew.repository.UserRepository;

import java.util.List;

@Service
public class AuthorizationService {


    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(@Valid User user) throws InvalidCredentials, UnauthorizedUser {
        if (isEmpty(user.getUserName()) || isEmpty(user.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user.getUserName());
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}