package com.ichtus.braingame.multiplication.user;

import com.ichtus.braingame.multiplication.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    //@Query("SELECT u FROM Users u WHERE u.alias = ?1 ORDER BY u.id DESC")
    Optional<User> findByAlias(String userAlias);
}
