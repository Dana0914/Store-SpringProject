package kz.runtime.storespringproject.service;


import kz.runtime.storespringproject.entities.Users;
import kz.runtime.storespringproject.repos.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users findUserById(Long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }
}
