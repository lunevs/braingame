package com.ichtus.braingame.multiplication_old.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "MyUsers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    //*@SequenceGenerator(name="myusers_generator", sequenceName="myusers_seq", allocationSize = 1)
    //*@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="myusers_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alias;

    public User(String alias) {
        this(null, alias);
    }


}
