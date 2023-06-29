package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addUser(String alias) {
        return userRepository.save(new User(alias));
    }
}
