package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;

public interface UserService {
    /**
     * Add new user . *
     * @return new user
     */
    User addUser(String alias);
}
