package com.ichtus.braingame.multiplication.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name="users_generator", sequenceName="users_seq", allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="users_generator")
    private Long id;
    private String alias;

    public User(String alias) {
        this(null, alias);
    }
}
