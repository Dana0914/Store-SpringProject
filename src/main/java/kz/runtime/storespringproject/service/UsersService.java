package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Users;
import kz.runtime.storespringproject.repos.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;


    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findUsersById(Long id) {
        return usersRepository
                .findUsersById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Users> findAllUsers() {
        if (usersRepository.findAll().isEmpty()) {
            throw new RuntimeException("Users not found");
        }
        return usersRepository.findAll();
    }

    public Users createUser(Users user) {
        Optional<Users> existingUser = usersRepository.findUsersByUsername(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        return usersRepository.save(user);
    }
}
