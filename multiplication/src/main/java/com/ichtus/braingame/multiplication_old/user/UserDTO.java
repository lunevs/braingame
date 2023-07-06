package com.ichtus.braingame.multiplication_old.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserDTO {

    @NotBlank
    String alias;
}
