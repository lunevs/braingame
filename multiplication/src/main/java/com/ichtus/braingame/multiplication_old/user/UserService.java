package com.ichtus.braingame.multiplication_old.user;


public interface UserService {
    /**
     * Add new user . *
     * @return new user
     */
    User addUser(String alias);

    /**
     * Find user by alias . *
     * @return new user
     */
    User findByAlias(String alias);

}
